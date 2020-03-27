package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.thread.FileRefactorThread;
import com.kricko.tvshowupdater.utils.TvShowUtils;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 */
public class RefactorFiles {

	/**
	 * Method tidyFolders.
	 * @param existing boolean
	 */
	public static void tidyFolders(boolean existing) {

		Shows shows;
		try {
			List<String> directories = TvShowUtils.getListOfTidyUpDirs();
			if(!directories.isEmpty()){

				ExecutorService threadPool1 = Executors.newFixedThreadPool(directories.size());

				for(String dir:directories){
					threadPool1.execute(new FileRefactorThread(null, dir, null, Optional.empty()));
				}
				threadPool1.shutdown();

				try {
					threadPool1.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
				} catch (InterruptedException e) {
					return;
				}
			}

			if(existing){
				shows = TvShowUtils.getListOfShows();
				if(shows != null){
					List<Details> details = shows.getShows();
					ExecutorService threadPool2 = Executors.newFixedThreadPool(details.size());
					for(Details detail:details){
						Optional<String> seriesId = detail.getTvdbSeriesId().isPresent() ? detail.getTvdbSeriesId() : Optional.empty();
						threadPool2.execute(new FileRefactorThread(detail.getName(), detail.getPath(), detail.getSkip(), seriesId));
					}
					threadPool2.shutdown();

					try{
						threadPool2.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
					} catch (InterruptedException e) {
						return;
					}
				}
			}
		} catch (IOException | ParseException e) {
			System.err.println("Failed to refactor files " + e.getLocalizedMessage());
		}
	}

}
