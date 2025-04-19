package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.configuration.Config;
import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Item;
import com.kricko.tvshowupdater.model.Rss;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.utils.TvShowUtils;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXB;
import java.net.URI;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class DownloadShows {

    private DownloadShows() {
        // Private constructor to prevent instantiation
    }

    public static void doDownload(Config config, Shows shows) {
        Objects.requireNonNull(config, "Config cannot be null");
        Objects.requireNonNull(shows, "Shows cannot be null");

        if (config.getQBitTorrentConfig() == null) {
            throw new IllegalStateException("QBitTorrent configuration cannot be null");
        }

        List<Details> showsList = shows.shows();
        if (showsList != null) {
            Map<String, Path> newDownloadDetails = new HashMap<>();

            showsList.parallelStream().forEach(detail -> {
                log.info("Checking for new items for {}", detail.getName());
                detail.getRssFeedId().ifPresent(rssFeedId -> {
                    try {
                        Rss rss = parseRssFeed(config, rssFeedId);
                        if (rss != null) {
                            newDownloadDetails.putAll(processRssItems(config, detail, rss));
                        }
                    } catch (Exception e) {
                        log.error("Exception caught when trying to parse RSS feed for feed ID: {}, Error {}", rssFeedId, e.getMessage());
                    }
                });
            });

            newDownloadDetails.keySet().parallelStream().forEach(episode -> {
                log.info("Downloading {} to {}", episode, newDownloadDetails.get(episode));
            });
        }
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
                    downloadItems.putAll(TvShowUtils.downloadNewItems(config, item, detail));
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
