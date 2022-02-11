package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.thread.IdentifyPotentialMissingThread;
import com.kricko.tvshowupdater.utils.TvShowUtils;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.kricko.tvshowupdater.utils.Constants.FILE_MISSING_EPISODES;

/**
 */
public class IdentifyMissingEpisodes {

	/**
	 * Method identifyMissing.
	 */
	public static void identifyMissing() {

		try {
			Shows shows = TvShowUtils.getListOfShows();
			if(shows != null){
				Files.delete(Paths.get(FILE_MISSING_EPISODES));
				List<Details> details = shows.getShows();
				ExecutorService threadPool = Executors.newFixedThreadPool(details.size());
				for(Details detail:details){
					threadPool.execute(new IdentifyPotentialMissingThread(detail.getName(), detail.getPath(), detail.getIgnoreMissing()));
				}
				threadPool.shutdown();

				try{
					threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
				} catch (InterruptedException e) {
					System.err.println(e.getLocalizedMessage());
				}
			}
		} catch (IOException | ParseException | URISyntaxException e) {
			System.err.println("Failed to refactor files " + e.getLocalizedMessage());
		}
	}

}
