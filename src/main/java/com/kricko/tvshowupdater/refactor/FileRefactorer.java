package com.kricko.tvshowupdater.refactor;

import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Series;
import com.kricko.tvshowupdater.thetvdb.TheTVDBApi;
import com.kricko.tvshowupdater.utils.Constants;
import com.kricko.tvshowupdater.utils.TvShowUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.currentThread;

/**
 */
public class FileRefactorer {

	static TheTVDBApi tvdb = new TheTVDBApi(Constants.API_KEY);

	/**
	 * Method refactorFilesAddTitle.
	 * @param seriesName String
	 * @param files List<Path>
	 * @param parentDir String
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean refactorFilesAddTitle(String seriesName, List<Path> files, String parentDir,
												Optional<String> seriesId) throws IOException{
		if(files != null){
			System.out.println(currentThread().getName() + " - File list is not empty in " + parentDir);
			Pattern pattern = Pattern.compile(Constants.REGEX_SERIES_EPISODE);
			Pattern pattern2 = Pattern.compile(Constants.REGEX_SERIES_EPISODE2);

			List<Series> seriesList = null;
			if(seriesName != null){
				System.out.println(currentThread().getName() + " - Searching Series Name " + seriesName);
				seriesList = tvdb.searchSeries(seriesName, Constants.LANGUAGE);
			}

			Matcher itemMatcher = null;
			int[] episodeIds = null;
			String episodeName = null;

			for(Path file:files){
				itemMatcher = pattern.matcher(file.toString());
				while(itemMatcher.find()){
					episodeName = itemMatcher.group();
					System.out.println(currentThread().getName() + " - " + episodeName);
					episodeIds = TvShowUtils.getEpisodeIds(episodeName, "E", 1);
				} 
				if(episodeIds == null){
					itemMatcher = pattern2.matcher(file.toString());
					while(itemMatcher.find()){
						episodeName = itemMatcher.group();
						episodeIds = TvShowUtils.getEpisodeIds(episodeName, "X", 0);
					}
				}

				Episode ep = null;
				if(seriesList != null){
					if(episodeIds != null){
						System.out.println(String.format("%s - Series %d Episodes %d",
								currentThread().getName(), episodeIds[0], episodeIds[1]));
						ep = tvdb.getEpisode(seriesId.orElse(seriesList.get(0).getId()),
								episodeIds[0], episodeIds[1], Constants.LANGUAGE);
					}
				}

				String fileName = file.getFileName().toString();
				System.out.println(currentThread().getName() + " - Checking if need to refactor " + file.getParent().toString() + File.separatorChar +fileName);
				String ext = fileName.substring(fileName.lastIndexOf("."));
				String newFileName = (ep != null) ? TvShowUtils.buildFileName(ep)  + ext : episodeName + ext;

				String currentParentDir = file.getParent().getFileName().toString();
				String newDir = (parentDir == null || currentParentDir.equals("Season "+ episodeIds[0])) 
						? file.getParent().toString() : parentDir;
				
				Path target = Paths.get(newDir, newFileName.replaceAll("\"", ""));
				if(!file.toString().equals(target.toString())){
					System.out.println(currentThread().getName() + " - Moving " + file.toString() + " to " + target.toString());
					Files.move(file, target, StandardCopyOption.REPLACE_EXISTING);
				}

				assert parentDir != null;
				if(parentDir.equals(newDir)){
					return true;
				}
				System.out.println(currentThread().getName() + " - Parent dir " + parentDir + " new dir " + newDir);
			}
		}
		return false;
	}

	/**
	 * Method getDirectories.
	 * @param dir Path
	 * @return List<Path>
	 * @throws IOException
	 */
	public static List<Path> getDirectories(final Path dir) throws IOException {
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


	/**
	 * Method getMovieFiles.
	 * @param dir Path
	 * @return List<Path>
	 * @throws IOException
	 */
	public static List<Path> getMovieFiles(final Path dir) throws IOException {
		final List<Path> fileList = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (final Iterator<Path> it = stream.iterator(); it.hasNext();) {
				Path file = dir.resolve(it.next());
				if(!Files.isDirectory(file)){
					String filename = file.getFileName().toString();
					String contentType = Files.probeContentType(file);
					if((contentType != null && contentType.startsWith("video"))
							|| filename.endsWith(".mkv")
							&& !filename.contains("sample")
							&& !filename.contains("Sample")){
						System.out.println(filename + " content Type is "+ contentType);
						fileList.add(file);
					}
				}
			}
		}
		return fileList;
	}

	/**
	 * Method deleteDirectory.
	 * @param path Path
	 * @throws IOException
	 */
	public static void deleteDirectory(Path path) throws IOException{
		Files.walkFileTree(path, new FileVisitor<Path>() {

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc)
					throws IOException {
				System.out.println(currentThread().getName() + " - Deleting directory :"+ dir);
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				System.out.println(currentThread().getName() + " - Deleting file: "+file);
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) {
				System.out.println(exc.toString());
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
