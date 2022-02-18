package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.configuration.Config;
import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Item;
import com.kricko.tvshowupdater.model.Rss;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.utils.TvShowUtils;
import org.json.simple.parser.ParseException;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.currentThread;

/**
 */
public class DownloadShows {

	/**
	 * Method doDownload.
	 * @throws IOException
	 * @throws ParseException
	 */
	public static boolean doDownload(Config config, Shows shows) throws IOException, URISyntaxException,
			ParseException {

		AtomicBoolean newDownloads = new AtomicBoolean(false);

		for(Details detail:shows.getShows()){
			System.out.printf("%s - Checking for new items for %s%n", currentThread().getName(), detail.getName());
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
					System.err.println("Exception caught when trying to parse URL for " + detail.getName());
				}
			});
		}

		TvShowUtils.appendDirToTidyUpList();

		return newDownloads.get();
	}
}
