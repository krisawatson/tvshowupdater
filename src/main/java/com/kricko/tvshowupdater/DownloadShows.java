package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.configuration.Config;
import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Item;
import com.kricko.tvshowupdater.model.Rss;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.utils.TvShowUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXB;
import java.net.URI;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kricko.tvshowupdater.utils.TvShowUtils.downloadNewItems;

/**
 */
public class DownloadShows {

	private static final Logger log = LoggerFactory.getLogger(DownloadShows.class);

	public static void doDownload(Config config, Shows shows) {
		Map<String, Path> newDownloadDetails = new HashMap<>();

		shows.shows().parallelStream().forEach(detail -> {
			log.info("Checking for new items for {}", detail.getName());
			detail.getRssFeedId().ifPresent(rssFeedId -> {
				try {
					Rss rss = parseRssFeed(config, rssFeedId);
					if (rss != null) {
						newDownloadDetails.putAll(processRssItems(config, detail, rss));
					}
				} catch (Exception e) {
					log.error("Exception caught when trying to process RSS feed for {}", detail.getName(), e);
				}
			});
		});

		newDownloadDetails.keySet().parallelStream().forEach(episode -> {
			log.info("Downloading {} to {}", episode, newDownloadDetails.get(episode));
		});
	}

	private static Rss parseRssFeed(Config config, int rssFeedId) {
		try {
			String rssFeed = String.format(config.getRssFeed(), rssFeedId);
			URI rssFeedUrl = new URI(rssFeed);
			return JAXB.unmarshal(rssFeedUrl, Rss.class);
		} catch (Exception e) {
			log.error("Exception caught when trying to parse RSS feed for feed ID: {}, Error {}", rssFeedId, e.getMessage());
			return null;
		}
	}

	private static Map<String, Path> processRssItems(Config config, Details detail, Rss rss) {
		List<Item> items = rss.getChannel().getItem();
		Map<String, Path> downloadItems = new HashMap<>();
		if (items != null) {
			String regex = buildRegex(config.getShowRegex(), detail);
			items = TvShowUtils.removeDuplicateEpisodes(items, regex);
			items.parallelStream().forEach(item -> {
				try {
					downloadItems.putAll(downloadNewItems(config, item, detail));
				} catch (Throwable e) {
					log.error("Error during item download for show '{}': {}", detail.getName(), e.getMessage());
				}
			});
		}
		return downloadItems;
	}

	private static String buildRegex(String baseRegex, Details detail) {
		StringBuilder builder = new StringBuilder();
		builder.append(baseRegex.replace("NAME", detail.getRegexName()));
		detail.getSkipTorrentsWith().parallelStream().forEach(toSkip -> {
			builder.append("(?!.* ");
			builder.append(toSkip);
			builder.append(")");
		});
		builder.append(".*$");
		return builder.toString();
	}
}
