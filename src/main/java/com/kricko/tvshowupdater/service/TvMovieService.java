package com.kricko.tvshowupdater.service;

import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Series;
import com.kricko.tvshowupdater.thetvdb.TheTVDBApi;
import com.kricko.tvshowupdater.utils.Constants;
import com.kricko.tvshowupdater.utils.TvShowUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Thread.currentThread;

/**
 */
public class TvMovieService {

	static TheTVDBApi tvdb = new TheTVDBApi(Constants.API_KEY);
	private static final Pattern PATTERN = Pattern.compile(Constants.REGEX_SERIES_EPISODE);
	private static final Pattern PATTERN2 = Pattern.compile(Constants.REGEX_SERIES_EPISODE2);

	/**
	 * Method refactorFilesAddTitle.
	 * @param seriesName String
	 * @param files List<Path>
	 * @param parentDir String
	 * @return boolean
	 * @throws IOException
	 */
	public static List<String> refactorFilesAddTitle(String seriesName, List<Path> files, String parentDir,
												Optional<String> seriesId) throws IOException {
		List<String> removableFolders = new ArrayList<>();
		if(files != null){
			System.out.println(currentThread().getName() + " - File list is not empty in " + parentDir);

			List<Series> seriesList = null;
			if(seriesName != null){
				System.out.println(currentThread().getName() + " - Searching Series Name " + seriesName);
				seriesList = tvdb.searchSeries(seriesName, Constants.LANGUAGE);
			}

			int[] episodeIds = null;
			String episodeName = null;

			for(Path file:files){
				Matcher itemMatcher = PATTERN.matcher(file.toString());
				while(itemMatcher.find()){
					episodeName = itemMatcher.group();
					System.out.println(currentThread().getName() + " - Matched " + seriesName + " episode to " + episodeName);
					episodeIds = TvShowUtils.getEpisodeIds(episodeName, "E", 1);
				} 
				if(episodeIds == null){
					itemMatcher = PATTERN2.matcher(file.toString());
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
					System.out.println(currentThread().getName() + " - Moving " + file + " to " + target);
					Files.move(file, target, StandardCopyOption.REPLACE_EXISTING);
				}

				System.out.println(currentThread().getName() + " - Parent dir " + parentDir + " new dir " + newDir);

				/**
				 * This is when the file lives in a folder below the `Season` folder
				 * i.e. G:\TV Series P-Z\Peacemaker\Season 1\Peacemaker.2022.S01E01.1080p.WEB.H264-CAKES[rarbg]
				 * Then {@FileRefactorThread} will remove the sub folder
 				 */
				if(!currentParentDir.startsWith("Season")) {
					System.out.printf("Current Parent directory %s is not the Season folder%n",
									  currentParentDir);
					removableFolders.add(currentParentDir);
				}
			}
		}
		return removableFolders;
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
			for (Path path : stream) {
				Path subDir = dir.resolve(path);
				if (Files.isDirectory(subDir)) {
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

	public static List<String> identifyPotentialMissingEpisodes(final Path dir, final List<String> ignorable) throws IOException {
		List<Path> files = getMovieFiles(dir);
		int[] episodeIds = null;
		String episodeName;

		Map<Integer, SortedSet<Integer>> seasonEpisodes = new HashMap<>();
		SortedSet<Integer> episodes = new TreeSet<>();
		int season = 0;
		for(Path file:files) {
			Matcher itemMatcher = PATTERN.matcher(file.toString());
			while(itemMatcher.find()){
				episodeName = itemMatcher.group();
				episodeIds = TvShowUtils.getEpisodeIds(episodeName, "E", 1);
			}
			if(episodeIds == null){
				itemMatcher = PATTERN2.matcher(file.toString());
				while(itemMatcher.find()){
					episodeName = itemMatcher.group();
					episodeIds = TvShowUtils.getEpisodeIds(episodeName, "X", 0);
				}
			}

			if(null != episodeIds) {
				season = episodeIds[0];
				episodes.add(episodeIds[1]);
			}
		}
		seasonEpisodes.put(season, episodes);
		List<String> missingEpisodes = checkSeasonEpisodes(dir, seasonEpisodes, ignorable);
		for (String missing : missingEpisodes) {
			System.out.println(String.format("%s", missing));
		}
		return missingEpisodes;
	}

	private static List<String> checkSeasonEpisodes(Path dir, Map<Integer, SortedSet<Integer>> seasonEpisodes,
													List<String> ignorable) {
		List<String> missingEpisodes = new ArrayList<>();
		seasonEpisodes.keySet().forEach(s -> {
			SortedSet<Integer> eps = seasonEpisodes.get(s);
			Set<Integer> missing = getMissingNumbers(eps);
			missing = removeIgnorable(s, missing, ignorable);
			if (!missing.isEmpty()) {
				missingEpisodes.add(String.format("%s - Season %d, Episodes %s", dir, s, missing));
			}
		});
		return missingEpisodes;
	}

	private static Set<Integer> getMissingNumbers(SortedSet<Integer> episodes) {
		Set<Integer> missing = new HashSet<>();
		if (!episodes.isEmpty()) {
			for (int i = 1; i <= episodes.last(); i++) {
				if (!episodes.contains(i)) {
					missing.add(i);
				}
			}
		}
		return missing;
	}

	private static Set<Integer> removeIgnorable(int season, Set<Integer> missingEpisodes, List<String> ignorable) {
		return missingEpisodes.stream()
							  .map(episodeId -> String.format("S%02dE%02d", season, episodeId))
							  .filter(seasonEpisode -> !ignorable.contains(seasonEpisode))
							  .map(seasonEpisode -> Integer.parseInt(seasonEpisode.substring(4)))
							  .collect(Collectors.toSet());
	}
}
