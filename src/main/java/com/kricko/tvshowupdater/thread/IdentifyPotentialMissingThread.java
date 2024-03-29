package com.kricko.tvshowupdater.thread;

import com.kricko.tvshowupdater.service.TvMovieService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.kricko.tvshowupdater.utils.TvShowUtils.writeMissingEpisodesToFile;

@Slf4j
public class IdentifyPotentialMissingThread implements Runnable {

	private final String path;
	private final List<String> ignorable;

	public IdentifyPotentialMissingThread(String path, List<String> ignorable){
		this.path = path;
		this.ignorable = ignorable;
	}
	
	@Override
	public void run() {
		try {
			Path parentDir = Paths.get(path);
			List<String> missingSeasons = TvMovieService.identifyPotentialMissingSeasons(parentDir);
			writeMissingEpisodesToFile(missingSeasons);

			// Get the list of sub-directories and refactor the files
			log.info("Checking if there are missing episodes for {}", parentDir);
			List<Path> dirs = TvMovieService.getDirectories(parentDir);
			for(Path dir:dirs){
				List<String> missingEpisodes = TvMovieService.identifyPotentialMissingEpisodes(dir, ignorable);
				writeMissingEpisodesToFile(missingEpisodes);
			}

		} catch (IOException e) {
			log.error("Failed to run", e);
		}	
	}
}
