package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.configuration.Config;
import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Item;
import com.kricko.tvshowupdater.model.Rss;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.utils.TvShowUtils;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 */
@Slf4j
public class DownloadShows {

	/**
	 * Method doDownload.
	 */
	public static boolean doDownload(Config config, Shows shows) {

		AtomicBoolean newDownloads = new AtomicBoolean(false);

		for(Details detail:shows.getShows()){
			log.info("Checking for new items for {}", detail.getName());
			detail.getRssFeedId().ifPresent(rssFeedId -> {
				Rss rss;
				try {
					String rssFeed = String.format(config.getRssFeed(), rssFeedId);
					URL rssFeedUrl = new URL(rssFeed);
					rss = JAXB.unmarshal(rssFeedUrl, Rss.class);
					List<Item> items = rss.getChannel().getItem();
					if(items != null){
						String regex = config.getShowRegex().replaceAll("NAME", detail.getRegexName());
						items = TvShowUtils.removeDuplicateEpisodes(items, regex);
						for(Item item:items){
							try {
								newDownloads.set(TvShowUtils.downloadNewItems(config, item, detail) || newDownloads.get());
							} catch (Throwable e) {
								log.error(e.getLocalizedMessage());
							}
							log.info(detail.getPath());
							log.info(item.getTitle());
						}
					}
				} catch (DataBindingException | MalformedURLException e) {
					log.error("Exception caught when trying to parse URL for {}", detail.getName());
				}
			});
		}

		TvShowUtils.appendDirToTidyUpList();

		return newDownloads.get();
	}
}
