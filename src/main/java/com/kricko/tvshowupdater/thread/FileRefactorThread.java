package com.kricko.tvshowupdater.thread;

import com.kricko.tvshowupdater.service.TvMovieService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Slf4j
public class FileRefactorThread implements Runnable {

	private String name, path;
	private List<Integer> skip;
	private Optional<String> seriesId;
	
	public FileRefactorThread(String name, String path, List<Integer> skip, Optional<String> seriesId){
		this.name = name;
		this.path = path;
		this.skip = skip;
		this.seriesId = seriesId;
	}
	
	@Override
	public void run() {
		try {
			// Get the list of sub-directories and refactor the files
			List<Path> dirs = TvMovieService.getDirectories(Paths.get(path));

			for(Path dir:dirs){
				String dirPathName = dir.getFileName().toString();
				int season = 0;
				if (dirPathName.startsWith("Season ")) {
					season = parseInt(dirPathName.substring(7));
				}
				if(skip == null || !skip.contains(season)){
					List<Path> files = TvMovieService.getMovieFiles(dir);

					List<String> dirsToRemove = TvMovieService.refactorFilesAddTitle(name, files, path, seriesId);
					for (String remove : dirsToRemove) {
						log.info("About to delete dir {}", remove);
						TvMovieService.deleteDirectory(dir);
					}
				}
			}

		} catch (IOException e) {
			log.error("Failed to run File Refactor", e);
		}
	}
}
