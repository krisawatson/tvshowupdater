package com.kricko.tvshowupdater;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.json.simple.parser.ParseException;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Item;
import com.kricko.tvshowupdater.model.Rss;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.utils.TvShowUtils;

/**
 */
public class DownloadShows {

	/**
	 * Method doDownload.
	 * @throws JAXBException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static boolean doDownload() throws JAXBException, IOException, ParseException {
		JAXBContext jc = JAXBContext.newInstance(Rss.class);

		Unmarshaller unmarshaller = jc.createUnmarshaller();

		Shows shows = TvShowUtils.getListOfShows();

		boolean newDownloads = false;

		for(Details detail:shows.getShows()){
			System.out.println(detail.getName());
			URL rssFeed = new URL(detail.getRssfeed());
			InputStream is = rssFeed.openStream();
			Rss rss = (Rss) unmarshaller.unmarshal(is);

			List<Item> items = rss.getChannel().getItem();
			if(items != null){
				items = TvShowUtils.removeDuplicateEpisodes(items, detail.getRegex());
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
			is.close();
		}

		TvShowUtils.appendDirToTidyUpList();

		return newDownloads;
	}
}
