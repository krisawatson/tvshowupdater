package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.thread.FileRefactorThread;
import com.kricko.tvshowupdater.utils.TvShowUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 */
public class RefactorFiles {

	private static final Logger log = LoggerFactory.getLogger(RefactorFiles.class);

	public static void tidyFolders(boolean existing, Shows shows) {
		Set<Details> details = existing ? new HashSet<>(shows.getShows()) : getDetailsForTidyUp(shows);
		if (!details.isEmpty()) {
			addTitleAndRename(details);
		}
	}

	private static Set<Details> getDetailsForTidyUp(Shows shows) {
		List<String> directories = TvShowUtils.getListOfTidyUpDirs();
		Set<Details> details = new HashSet<>();

		if (!directories.isEmpty()) {
			for (String dir : directories) {
				details.addAll(shows.getShows()
									.stream()
									.filter(showDetails -> dir.replaceAll("\\\\", "/").startsWith(showDetails.getPath()))
									.collect(Collectors.toList()));
			}
		}

		return details;
	}

	public static void addTitleAndRename(Set<Details> details) {
		ExecutorService threadPool = Executors.newFixedThreadPool(details.size());

		details.parallelStream().forEach(detail -> {
			Optional<String> seriesId = detail.getTvdbSeriesId();
			threadPool.execute(new FileRefactorThread(detail.getName(), detail.getPath(),
													  detail.getSkipSeason(), seriesId));
		});

		threadPool.shutdown();
		try {
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			log.error("Failed to terminate the thread pool", e);
		}
	}
}
