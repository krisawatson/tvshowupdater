package com.kricko.tvshowupdater.thread;

import com.kricko.tvshowupdater.service.TvMovieService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.kricko.tvshowupdater.utils.TvShowUtils.writeMissingEpisodesToFile;

public class IdentifyPotentialMissingThread implements Runnable {

	private String name, path;

	public IdentifyPotentialMissingThread(String name, String path){
		this.name = name;
		this.path = path;
	}
	
	@Override
	public void run() {
		try {
			// Get the list of sub-directories and refactor the files
			List<Path> dirs = TvMovieService.getDirectories(Paths.get(path));

			for(Path dir:dirs){
				List<String> missing = TvMovieService.identifyPotentialMissingEpisodes(dir);
				writeMissingEpisodesToFile(missing);
			}

		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}	
	}
}
