package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Item;
import com.kricko.tvshowupdater.model.Rss;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.utils.Config;
import com.kricko.tvshowupdater.utils.TvShowUtils;
import org.json.simple.parser.ParseException;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 */
public class DownloadShows {

	private static final String REGEX_PROP = "setting.show_regex";
	private static final Config config = Config.getInstance();
	/**
	 * Method doDownload.
	 * @throws IOException
	 * @throws ParseException
	 */
	public static boolean doDownload() throws IOException, ParseException, URISyntaxException {
		Shows shows = TvShowUtils.getListOfShows();

		boolean newDownloads = false;

		for(Details detail:shows.getShows()){
			System.out.println(detail.getName());
			URL rssFeed = new URL(detail.getRssfeed());
			Rss rss;
			try {
				rss = JAXB.unmarshal(rssFeed, Rss.class);
			} catch (DataBindingException dbe) {
				System.err.println("Exception caught when trying to parse URL for " + detail.getName());
				continue;
			}

			List<Item> items = rss.getChannel().getItem();
			if(items != null){
				String regex = config.getProperty(REGEX_PROP).replaceAll("NAME", detail.getRegexName());
				items = TvShowUtils.removeDuplicateEpisodes(items, regex);
				for(Item item:items){
					try {
						newDownloads = TvShowUtils.downloadNewItems(item, detail) || newDownloads;
					} catch (Throwable e) {
						System.err.println(e.getLocalizedMessage());
					}
					System.out.println(detail.getPath());
					System.out.println(item.getTitle());
				}
			}
		}

		TvShowUtils.appendDirToTidyUpList();

		return newDownloads;
	}
}
