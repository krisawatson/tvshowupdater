package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.thread.IdentifyPotentialMissingThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class IdentifyMissing {
	private static final Logger log = LoggerFactory.getLogger(IdentifyMissing.class);

	/**
	 * Method identifyMissingSeasons.
	 */
	public static void identifyMissing(Shows shows) {
		try {
			if (shows != null) {
				Files.deleteIfExists(Paths.get(FILE_MISSING));
				List<Details> details = shows.getShows();
				identifyMissingInParallel(details);
			}
		} catch (IOException e) {
			log.error("Failed to identify missing seasons", e);
		}
	}

	private static void identifyMissingInParallel(List<Details> details) {
		ExecutorService threadPool = Executors.newFixedThreadPool(details.size());
		try {
			details.parallelStream().forEach(detail -> threadPool.execute(
					new IdentifyPotentialMissingThread(detail.getPath(), detail.getIgnoreMissing())));

			threadPool.shutdown();
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			log.error("Thread interrupted", e);
		} finally {
			if (!threadPool.isTerminated()) {
				log.warn("Some threads didn't terminate properly");
			}
		}
	}
}
