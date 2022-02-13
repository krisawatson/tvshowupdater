package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.thread.FileRefactorThread;
import com.kricko.tvshowupdater.utils.TvShowUtils;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.kricko.tvshowupdater.utils.TvShowUtils.getListOfShows;

/**
 */
public class RefactorFiles {

	/**
	 * Method tidyFolders.
	 * @param existing boolean
	 */
	public static void tidyFolders(boolean existing) {

		try {
			Shows shows = getListOfShows();
			Set<Details> details = new HashSet<>();
			List<String> directories = Collections.emptyList();
			if (existing) {
				if(shows != null) {
					details = new HashSet<>(shows.getShows());
				}
			} else {
				directories = TvShowUtils.getListOfTidyUpDirs();
				if (!directories.isEmpty()) {
					for (String dir : directories) {
						details.addAll(shows.getShows()
									   .stream()
									   .filter(showDetails -> dir.replaceAll("\\\\", "/")
																 .startsWith(showDetails.getPath()))
									   .collect(Collectors.toList()));
					}
				}
			}
			addTitleAndRename(details, directories);
		} catch (IOException | ParseException | URISyntaxException e) {
			System.err.println("Failed to refactor files " + e.getLocalizedMessage());
		}
	}

	public static void addTitleAndRename(Set<Details> details, List<String> directories) {
		ExecutorService threadPool = Executors.newFixedThreadPool(details.size());
		if (!directories.isEmpty()) {
			directories.forEach(dir -> threadPool.execute(new FileRefactorThread(null, dir, null, null)));
		}
		if (!details.isEmpty()) {
			for(Details detail:details){
				Optional<String> seriesId = detail.getTvdbSeriesId().isPresent() ? detail.getTvdbSeriesId() : Optional.empty();
				threadPool.execute(new FileRefactorThread(detail.getName(), detail.getPath(), detail.getSkip(), seriesId));
			}
		}
		threadPool.shutdown();
		try{
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			System.err.println("Failed to terminate the thread pool");
		}
	}

}
