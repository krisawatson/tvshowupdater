package com.kricko.tvshowupdater.refactor;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Series;
import com.kricko.tvshowupdater.thetvdb.TheTVDBApi;
import com.kricko.tvshowupdater.utils.Constants;
import com.kricko.tvshowupdater.utils.TvShowUtils;

public class FileRefactorer {

	TheTVDBApi tvdb = new TheTVDBApi(Constants.API_KEY);

	public void doRefactor(String seriesName, String parentDirectory){
		try {
			// Refactor any files in the parent directory first
			List<Path> files = getMovieFiles(Paths.get(parentDirectory));

			// Get the list of sub-directories and refactor the files
			List<Path> dirs = getDirectories(Paths.get(parentDirectory));

			for(Path dir:dirs){
				files = getMovieFiles(dir);

				if(refactorFilesAddTitle(seriesName, files, parentDirectory)){
					deleteDirectory(dir);
				}
			}

		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

	private boolean refactorFilesAddTitle(String seriesName, List<Path> files, String parentDir) throws IOException{
		if(files != null){
			Pattern pattern = Pattern.compile(Constants.REGEX_SERIES_EPISODE);

			List<Series> seriesList = null;
			if(seriesName != null){
				System.out.println("Searching Series Name " + seriesName);
				seriesList = tvdb.searchSeries(seriesName, Constants.LANGUAGE);
			}

			Matcher itemMatcher = null;
			int[] episodeIds = null;
			String episodeName = null;

			for(Path file:files){
				itemMatcher = pattern.matcher(file.toString());
				while(itemMatcher.find()){
					episodeName = itemMatcher.group();
					System.out.println(episodeName);
					episodeIds = TvShowUtils.getEpisodeIds(episodeName);
				}

				Episode ep = null;
				if(seriesList != null){
					if(episodeIds != null){
						System.out.println(String.format("Series %d Episodes %d", episodeIds[0], episodeIds[1]));
						ep = tvdb.getEpisode(seriesList.get(0).getId(), episodeIds[0], episodeIds[1], Constants.LANGUAGE);
					}
				}

				String fileName = file.getFileName().toString();
				System.out.println("Checking if need to refactor " + file.getParent().toString() + File.separatorChar +fileName);
				String ext = fileName.substring(fileName.lastIndexOf("."));
				String newFileName = (ep != null) ? TvShowUtils.buildFileName(ep)  + ext : episodeName + ext;

				String currentParentDir = file.getParent().getFileName().toString();
				String newDir = (parentDir == null || currentParentDir.equals("Season "+ episodeIds[0])) 
						? file.getParent().toString() : parentDir;
				
				Path target = Paths.get(newDir.toString(), newFileName);
				System.out.println("Moving " + file.toString() + " to " + target.toString());

				Files.move(file, target, StandardCopyOption.REPLACE_EXISTING);

				if(parentDir.equals(newDir)){
					return true;
				}
			}
		}
		return false;
	}

	private List<Path> getDirectories(final Path dir) throws IOException {
		final List<Path> dirlist = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (final Iterator<Path> it = stream.iterator(); it.hasNext();) {
				Path subDir = dir.resolve(it.next());
				if(Files.isDirectory(subDir)){
					dirlist.add(subDir);
				}
			}
		}
		return dirlist;
	}


	private List<Path> getMovieFiles(final Path dir) throws IOException {
		final List<Path> fileList = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (final Iterator<Path> it = stream.iterator(); it.hasNext();) {
				Path file = dir.resolve(it.next());
				if(!Files.isDirectory(file)){
					String contentType = Files.probeContentType(file);
					if(contentType != null && contentType.startsWith("video")
							&& !file.toString().contains("sample")
							&& !file.toString().contains("Sample")){
						System.out.println(file.toString() + " content Type is "+ contentType);
						fileList.add(file);
					}
				}
			}
		}
		return fileList;
	}

	private void deleteDirectory(Path path) throws IOException{
		Files.walkFileTree(path, new FileVisitor<Path>() {

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc)
					throws IOException {
				System.out.println("deleting directory :"+ dir);
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult preVisitDirectory(Path dir,
					BasicFileAttributes attrs) throws IOException {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException {
				System.out.println("Deleting file: "+file);
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc)
					throws IOException {
				System.out.println(exc.toString());
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
