package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.thread.IdentifyPotentialMissingThread;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.kricko.tvshowupdater.utils.Constants.FILE_MISSING;

/**
 */
@Slf4j
public class IdentifyMissing {

	/**
	 * Method identifyMissingSeasons.
	 */
	public static void identifyMissing(Shows shows) {
		try {
			if(shows != null){
				Files.deleteIfExists(Paths.get(FILE_MISSING));
				List<Details> details = shows.getShows();
				ExecutorService threadPool = Executors.newFixedThreadPool(details.size());
				for(Details detail:details){
					threadPool.execute(new IdentifyPotentialMissingThread(detail.getName(), detail.getPath(), detail.getIgnoreMissing()));
				}
				threadPool.shutdown();

				try{
					threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
				} catch (InterruptedException e) {
					log.error("Thread interrupted", e);
				}
			}
		} catch (IOException e) {
			log.error("Failed to refactor files", e);
		}
	}
}
