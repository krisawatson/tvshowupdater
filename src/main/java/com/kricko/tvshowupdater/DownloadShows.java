package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.configuration.Config;
import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Item;
import com.kricko.tvshowupdater.model.Rss;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.utils.TvShowUtils;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kricko.tvshowupdater.utils.Logger.logError;
import static com.kricko.tvshowupdater.utils.Logger.logLine;

/**
 */
public class DownloadShows {

	/**
	 * Method doDownload.
	 */
	public static boolean doDownload(Config config, Shows shows) {

		AtomicBoolean newDownloads = new AtomicBoolean(false);

		for(Details detail:shows.getShows()){
			logLine(String.format("Checking for new items for %s", detail.getName()), DownloadShows.class.getSimpleName());
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
								System.err.println(e.getLocalizedMessage());
							}
							System.out.println(detail.getPath());
							System.out.println(item.getTitle());
						}
					}
				} catch (DataBindingException | MalformedURLException e) {
					logError(String.format("Exception caught when trying to parse URL for %s", detail.getName()),
							 DownloadShows.class.getSimpleName());
				}
			});
		}

		TvShowUtils.appendDirToTidyUpList();

		return newDownloads.get();
	}
}
