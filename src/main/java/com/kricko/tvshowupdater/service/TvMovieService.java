package com.kricko.tvshowupdater.service;

import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Series;
import com.kricko.tvshowupdater.thetvdb.TheTVDBApi;
import com.kricko.tvshowupdater.utils.Constants;
import com.kricko.tvshowupdater.utils.TvShowUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

/**
 */
@Slf4j
public class TvMovieService {

	static TheTVDBApi tvdb = new TheTVDBApi(Constants.API_KEY);
	private static final Pattern PATTERN = Pattern.compile(Constants.REGEX_SERIES_EPISODE);
	private static final Pattern PATTERN2 = Pattern.compile(Constants.REGEX_SERIES_EPISODE2);
	private static final Pattern SEASON_PATTERN = Pattern.compile(Constants.REGEX_SEASON);

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
			log.info("File list is not empty in {}", parentDir);

			List<Series> seriesList = null;
			if(seriesName != null){
				log.info("Searching Series Name {}", seriesName);
				seriesList = tvdb.searchSeries(seriesName, Constants.LANGUAGE);
			}

			int[] episodeIds = null;
			String episodeName = null;

			for(Path file:files){
				Matcher itemMatcher = PATTERN.matcher(file.toString());
				while(itemMatcher.find()){
					episodeName = itemMatcher.group();
					log.info("Matched {} episode to {}", seriesName, episodeName);
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
						log.info("Series {} Episodes {}}", episodeIds[0], episodeIds[1]);
						ep = tvdb.getEpisode(seriesId.orElse(seriesList.get(0).getId()),
								episodeIds[0], episodeIds[1], Constants.LANGUAGE);
					}
				}

				String fileName = file.getFileName().toString();
				String filePath = file.getParent().toString() + File.separatorChar +fileName;
				log.info("Checking if need to refactor {}", filePath);
				String ext = fileName.substring(fileName.lastIndexOf("."));
				String newFileName = (ep != null)
									 ? TvShowUtils.buildFileName(ep)  + ext
									 : (null != episodeName)
									   ? episodeName + ext
									   : fileName;

				String currentParentDir = file.getParent().getFileName().toString();
				String newDir = (parentDir == null || currentParentDir.startsWith("Season"))
								? file.getParent().toString()
								: parentDir;
				
				Path target = Paths.get(newDir, newFileName.replaceAll("\"", "").replace("*", "_"));
				if(!file.toString().equals(target.toString())){
					log.info("Moving {} to {}", file, target);
					Files.move(file, target, StandardCopyOption.REPLACE_EXISTING);
				}

				log.info("Parent dir {} new dir {}", parentDir, newDir);

				/**
				 * This is when the file lives in a folder below the `Season` folder
				 * i.e. G:\TV Series P-Z\Peacemaker\Season 1\Peacemaker.2022.S01E01.1080p.WEB.H264-CAKES[rarbg]
				 * Then {@FileRefactorThread} will remove the sub folder
 				 */
				if(!currentParentDir.startsWith("Season")) {
					log.info("Current Parent directory {} is not the Season folder", currentParentDir);
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
			for (Path path : stream) {
				Path file = dir.resolve(path);
				if (!Files.isDirectory(file)) {
					String filename = file.getFileName().toString();
					String contentType = Files.probeContentType(file);
					if ((contentType != null && contentType.startsWith("video"))
							|| filename.endsWith(".mkv")
							&& !filename.contains("sample")
							&& !filename.contains("Sample")) {
						fileList.add(file);
					}
				}
			}
		}
		return fileList;
	}

	/**
	 * Method getMovieFiles.
	 * @param dir Path
	 * @return List<Path>
	 * @throws IOException
	 */
	private static List<Path> getSeasonDirs(final Path dir) throws IOException {
		final List<Path> list = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (Path path : stream) {
				Path file = dir.resolve(path);
				if (Files.isDirectory(file)) {
					String seasonName = file.getFileName().toString();
					if (seasonName.startsWith("Season")) {
						list.add(file);
					}
				}
			}
		}
		return list;
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
				log.info("Deleting directory: {}", dir);
				Files.deleteIfExists(dir);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				log.info("Deleting file: {}", file);
				Files.deleteIfExists(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) {
				log.warn("Failed to visit file", exc);
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
		return checkSeasonEpisodes(dir, seasonEpisodes, ignorable);
	}



	public static List<String> identifyPotentialMissingSeasons(final Path dir) throws IOException {
		List<Path> seasons = getSeasonDirs(dir);
		SortedSet<Integer> seasonIds = new TreeSet<>();
		List<String> missingSeasons = new ArrayList<>();

		for(Path seasonDir:seasons) {
			String seasonDirPath = seasonDir.toString();
			seasonIds.add(parseInt(seasonDirPath.substring(seasonDirPath.lastIndexOf("\\") + 8)));
		}
		Set<Integer> missingSeasonIds = getMissingNumbers(seasonIds);
		missingSeasonIds.forEach(season -> missingSeasons.add(String.format("%s\\Season %d", dir, season)));
		return missingSeasons;
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

	private static Set<Integer> getMissingNumbers(SortedSet<Integer> sortedSet) {
		Set<Integer> missing = new HashSet<>();
		if (!sortedSet.isEmpty()) {
			for (int i = 1; i <= sortedSet.last(); i++) {
				if (!sortedSet.contains(i)) {
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
							  .map(seasonEpisode -> parseInt(seasonEpisode.substring(4)))
							  .collect(Collectors.toSet());
	}
}
