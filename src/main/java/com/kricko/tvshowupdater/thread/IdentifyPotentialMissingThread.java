package com.kricko.tvshowupdater.thread;

import com.kricko.tvshowupdater.service.TvMovieService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.kricko.tvshowupdater.utils.TvShowUtils.writeMissingEpisodesToFile;

public class IdentifyPotentialMissingThread implements Runnable {

	private final String name, path;
	private final List<String> ignorable;

	public IdentifyPotentialMissingThread(String name, String path, List<String> ignorable){
		this.name = name;
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
			List<Path> dirs = TvMovieService.getDirectories(parentDir);
			for(Path dir:dirs){
				List<String> missingEpisodes = TvMovieService.identifyPotentialMissingEpisodes(dir, ignorable);
				writeMissingEpisodesToFile(missingEpisodes);
			}

		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}	
	}
}
