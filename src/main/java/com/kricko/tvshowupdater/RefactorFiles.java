package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.thread.FileRefactorThread;
import com.kricko.tvshowupdater.utils.TvShowUtils;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
			List<Details> details = Collections.emptyList();
			if (existing) {
				if(shows != null) {
					details = shows.getShows();
				}
			} else {
				List<String> directories = TvShowUtils.getListOfTidyUpDirs();
				if (!directories.isEmpty()) {
					for (String dir : directories) {
						details = shows.getShows()
									   .stream()
									   .filter(showDetails -> dir.replaceAll("\\\\", "/")
																 .startsWith(showDetails.getPath()))
									   .collect(Collectors.toList());
					}
				}
			}
			addTitleAndRename(details);
		} catch (IOException | ParseException | URISyntaxException e) {
			System.err.println("Failed to refactor files " + e.getLocalizedMessage());
		}
	}

	public static void addTitleAndRename(List<Details> details) {
		if (!details.isEmpty()) {
			ExecutorService threadPool2 = Executors.newFixedThreadPool(details.size());
			for(Details detail:details){
				Optional<String> seriesId = detail.getTvdbSeriesId().isPresent() ? detail.getTvdbSeriesId() : Optional.empty();
				threadPool2.execute(new FileRefactorThread(detail.getName(), detail.getPath(), detail.getSkip(), seriesId));
			}
			threadPool2.shutdown();

			try{
				threadPool2.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			} catch (InterruptedException e) {
				System.err.println("Failed to terminate the thread pool");
			}
		}
	}

}
