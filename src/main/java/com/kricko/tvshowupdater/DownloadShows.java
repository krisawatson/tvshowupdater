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
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 */
public class DownloadShows {

	private static final Logger log = LoggerFactory.getLogger(DownloadShows.class);

	public static boolean doDownload(Config config, Shows shows) {
		AtomicBoolean newDownloads = new AtomicBoolean(false);

		for (Details detail : shows.getShows()) {
			log.info("Checking for new items for {}", detail.getName());
			detail.getRssFeedId().ifPresent(rssFeedId -> {
				try {
					Rss rss = parseRssFeed(config, rssFeedId);
					if (rss != null) {
						processRssItems(config, detail, newDownloads, rss);
					}
				} catch (Exception e) {
					log.error("Exception caught when trying to process RSS feed for {}", detail.getName(), e);
				}
			});
		}

		TvShowUtils.appendDirToTidyUpList();

		return newDownloads.get();
	}

	private static Rss parseRssFeed(Config config, int rssFeedId) {
		try {
			String rssFeed = String.format(config.getRssFeed(), rssFeedId);
			URL rssFeedUrl = new URL(rssFeed);
			return JAXB.unmarshal(rssFeedUrl, Rss.class);
		} catch (Exception e) {
			log.error("Exception caught when trying to parse RSS feed for feed ID: {}", rssFeedId, e);
			return null;
		}
	}

	private static void processRssItems(Config config, Details detail, AtomicBoolean newDownloads, Rss rss) {
		List<Item> items = rss.getChannel().getItem();
		if (items != null) {
			String regex = config.getShowRegex().replaceAll("NAME", detail.getRegexName());
			items = TvShowUtils.removeDuplicateEpisodes(items, regex);
			for (Item item : items) {
				try {
					newDownloads.set(TvShowUtils.downloadNewItems(config, item, detail) || newDownloads.get());
				} catch (Throwable e) {
					log.error("Error during item download for show '{}': {}", detail.getName(), e.getMessage());
				}
				log.info("Show Path: {}", detail.getPath());
				log.info("Item Title: {}", item.getTitle());
			}
		}
	}
}
