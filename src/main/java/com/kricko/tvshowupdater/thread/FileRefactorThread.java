package com.kricko.tvshowupdater.thread;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.kricko.tvshowupdater.refactor.FileRefactorer;

public class FileRefactorThread implements Runnable {

	private String name, path;
	private List<String> skip;
	
	public FileRefactorThread(String name, String path, List<String> skip){
		this.name = name;
		this.path = path;
		this.skip = skip;
	}
	
	@Override
	public void run() {
		try {
			// Refactor any files in the parent directory first
			List<Path> files = FileRefactorer.getMovieFiles(Paths.get(path));

			// Get the list of sub-directories and refactor the files
			List<Path> dirs = FileRefactorer.getDirectories(Paths.get(path));

			for(Path dir:dirs){
				if(skip == null || !skip.contains(dir.getFileName().toString())){
					files = FileRefactorer.getMovieFiles(dir);
	
					if(FileRefactorer.refactorFilesAddTitle(name, files, path)){
						FileRefactorer.deleteDirectory(dir);
					}
				}
			}

		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}	
	}
}