package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.configuration.Config;
import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Item;
import com.kricko.tvshowupdater.model.Rss;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.oneom.OneOmClient;
import com.kricko.tvshowupdater.oneom.Torrent;
import com.kricko.tvshowupdater.utils.TvShowUtils;
import org.json.simple.parser.ParseException;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kricko.tvshowupdater.utils.Constants.EXCLUDED_IN_TORRENT_NAME;
import static com.kricko.tvshowupdater.utils.TvShowUtils.episodeDoesntExist;
import static java.lang.Integer.parseInt;
import static java.lang.Thread.currentThread;
import static java.util.stream.Collectors.toList;

/**
 */
public class DownloadShows {

	private static final long MAX_TORRENT_SIZE = 10L * 1024 * 1024 * 1024;

	/**
	 * Method doDownload.
	 * @throws IOException
	 * @throws ParseException
	 */
	public static boolean doDownload(Config config, Shows shows) {

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

	public static boolean downloadFromOneOm(Config config, Shows shows) {

		AtomicBoolean newDownloads = new AtomicBoolean(false);

		for(Details detail:shows.getShows()){
			System.out.printf("[%s] - Checking for new items for %s%n", currentThread().getName(), detail.getName());
			detail.getOneomId().ifPresent(oneomId -> {
				try {
					var seriesDetails = OneOmClient.getSeriesDetails(config, oneomId);
					seriesDetails.getEpisodeList().forEach(episode -> {
						Path dir = Paths.get(detail.getPath() + File.separatorChar
													 + "Season " + parseInt(episode.getSeason()));
						int seasonInt = parseInt(episode.getSeason());
						int episodeInt = parseInt(episode.getEpisode());

						if (!detail.getSkipSeason().contains(seasonInt)
								&& episodeDoesntExist(dir, seasonInt, episodeInt, detail.getIgnoreMissing())) {
							var torrents = episode.getTorrent()
												  .stream()
												  .filter(torrent -> MAX_TORRENT_SIZE > torrent.getSize())
												  .filter(torrent -> !EXCLUDED_IN_TORRENT_NAME.contains(torrent.getTitle()))
												  .collect(toList());
							if (!torrents.isEmpty()) {
								torrents.sort(Comparator.comparingInt(Torrent::getSeeders).reversed());
								System.out.printf("Top seeders %d, min seeders %d%n", torrents.get(0).getSeeders(),
												  torrents.get(torrents.size() - 1).getSeeders());
								System.out.printf("Downloading %s - S%sE%s from with link:%n%s%n",
												  seriesDetails.getTitle(), episode.getSeason(), episode.getEpisode(),
												  torrents.get(0).getLink());
								newDownloads.set(true);
								TvShowUtils.downloadMagnetLink(config, torrents.get(0).getLink(), dir);
							}
						}
					});
				} catch (DataBindingException | IOException e) {
					System.err.println("Exception caught when trying to parse URL for " + detail.getName());
				}
			});
		}

		TvShowUtils.appendDirToTidyUpList();

		return newDownloads.get();
	}
}
