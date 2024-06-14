package com.kricko.tvshowupdater.service;

import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Series;
import com.kricko.tvshowupdater.thetvdb.TheTVDBApi;
import com.kricko.tvshowupdater.utils.Constants;
import com.kricko.tvshowupdater.utils.TvShowUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.*;
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

	/**
	 * Method refactorFilesAddTitle.
	 * @param seriesName String
	 * @param files List<Path>
	 * @param parentDir String
	 * @return boolean
     */
	public static List<String> refactorFilesAddTitle(String seriesName, List<Path> files, String parentDir,
													 Optional<String> seriesId) throws IOException {
		List<String> removableFolders = new ArrayList<>();

		if (!files.isEmpty()) {
			log.info("File list is not empty in {}", parentDir);

			List<Series> seriesList = (seriesName != null) ? tvdb.searchSeries(seriesName, Constants.LANGUAGE) : null;

			files.parallelStream().forEach(file -> processFile(seriesName, seriesId, seriesList, file, parentDir, removableFolders));
		}

		return removableFolders;
	}

	/**
	 * Method getDirectories.
	 * @param dir Path
	 * @return List<Path>
     */
	public static List<Path> getDirectories(final Path dir) throws IOException {
		if (Files.exists(dir) && Files.isDirectory(dir)) {
			return Files.list(dir)
						.filter(Files::isDirectory)
						.collect(Collectors.toList());
		}
		return List.of();
	}

	/**
	 * Get a list of movie files from a given directory.
	 * @param dir Path
	 * @return List<Path>
     */
	public static List<Path> getMovieFiles(final Path dir) throws IOException {
		final List<Path> fileList = new ArrayList<>();
		traverseDirectory(dir, fileList);
		return fileList;
	}

	/**
	 * Method getMovieFiles.
	 * @param dir Path
	 * @return List<Path>
     */
	public static List<Path> getSeasonDirs(final Path dir) throws IOException {
		List<Path> seasonDirs = new ArrayList<>();

		if (Files.exists(dir) && Files.isDirectory(dir)) {
			SeasonDirectoryVisitor seasonDirectoryVisitor = new SeasonDirectoryVisitor();
			Files.walkFileTree(dir, EnumSet.noneOf(FileVisitOption.class), 1, seasonDirectoryVisitor);
			seasonDirs = seasonDirectoryVisitor.getSeasonDirs();
		}

		return seasonDirs;
	}

	/**
	 * Method deleteDirectory.
	 * @param path Path
     */
	public static void deleteDirectory(Path path, String remove) throws IOException{
		Path removable = Path.of(path.toString(), remove);
		Files.walkFileTree(removable, new RemovableDirectoryVisitor());
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

	private static void processFile(String seriesName, Optional<String> seriesId, List<Series> seriesList,
									Path file, String parentDir, List<String> removableFolders) {
		Matcher itemMatcher = PATTERN.matcher(file.toString());
		int[] episodeIds = null;
		String episodeName = null;

		while (itemMatcher.find()) {
			episodeName = itemMatcher.group();
			log.info("Matched {} episode to {}", seriesName, episodeName);
			episodeIds = TvShowUtils.getEpisodeIds(episodeName, "E", 1);
		}

		if (episodeIds == null) {
			itemMatcher = PATTERN2.matcher(file.toString());
			while (itemMatcher.find()) {
				episodeName = itemMatcher.group();
				episodeIds = TvShowUtils.getEpisodeIds(episodeName, "X", 0);
			}
		}

		Episode ep = getEpisodeDetails(seriesList, seriesId, episodeIds);

		String newFileName = buildNewFileName(ep, episodeName, file);

		String currentParentDir = file.getParent().getFileName().toString();
		String newDir = determineNewDir(parentDir, currentParentDir, file);

        try {
            moveFileIfNeeded(file, newFileName, currentParentDir, newDir);
        } catch (IOException e) {
            log.error("Unable to move file {} to {} with name {}, {}",
					  file, newDir, newFileName, e.getLocalizedMessage());
			return;
        }

        handleRemovableFolders(currentParentDir, removableFolders);
	}

	private static Episode getEpisodeDetails(List<Series> seriesList, Optional<String> seriesId, int[] episodeIds) {
		Episode ep = null;

		if (seriesList != null && episodeIds != null) {
			log.info("Series {} Episodes {}", episodeIds[0], episodeIds[1]);
			ep = tvdb.getEpisode(seriesId.orElse(seriesList.get(0).getId()), episodeIds[0], episodeIds[1], Constants.LANGUAGE);
		}

		return ep;
	}

	private static String buildNewFileName(Episode ep, String episodeName, Path file) {
		String fileName = file.getFileName().toString();
		String ext = fileName.substring(fileName.lastIndexOf("."));
		return (ep != null) ? TvShowUtils.buildFileName(ep) + ext : (episodeName != null) ? episodeName + ext : fileName;
	}

	private static String determineNewDir(String parentDir, String currentParentDir, Path file) {
		String newDir;

		if (parentDir == null) {
			newDir = currentParentDir;  // Keep the existing directory if parentDir is not specified
		} else if (currentParentDir.startsWith("Season")) {
			newDir = file.getParent().toString();
		} else {
			// Append "Season" to the current parent directory
			newDir = findNearestSeasonDirectory(file.getParent()).toString();
		}

		return newDir;
	}

	private static Path findNearestSeasonDirectory(Path directory) {
		Path currentDir = directory;

		while (currentDir != null) {
			String dirName = currentDir.getFileName().toString();
			if (dirName.matches("Season\\s\\d+")) {
				return currentDir;
			}
			currentDir = currentDir.getParent();
		}

		return directory; // Return the original directory if no "Season" directory is found
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
		missingSeasonIds.parallelStream().forEach(season -> missingSeasons.add(String.format("%s\\Season %d", dir, season)));
		return missingSeasons;
	}

	private static List<String> checkSeasonEpisodes(Path dir, Map<Integer, SortedSet<Integer>> seasonEpisodes,
													List<String> ignorable) {
		List<String> missingEpisodes = new ArrayList<>();
		seasonEpisodes.keySet().parallelStream().forEach(s -> {
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



	private static void moveFileIfNeeded(Path file, String newFileName, String currentParentDir, String newDir) throws IOException {
		Path target = Paths.get(newDir, newFileName.replaceAll("\"", "").replace("*", "_"));
		if (!file.toString().equals(target.toString())) {
			log.info("Moving {} to {}", file, target);
			Files.move(file, target, StandardCopyOption.REPLACE_EXISTING);
		}
	}

	private static void handleRemovableFolders(String currentParentDir, List<String> removableFolders) {
		if (!currentParentDir.matches("Season\\s\\d+")) {
			log.info("Current Parent directory {} is not the Season folder", currentParentDir);
			removableFolders.add(currentParentDir);
		}
	}

	private static void traverseDirectory(Path dir, List<Path> fileList) throws IOException {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (Path path : stream) {
				Path file = dir.resolve(path);

				if (Files.isDirectory(file)) {
					// Recursively traverse subdirectories
					traverseDirectory(file, fileList);
				} else {
					// Check if it's a movie file based on your criteria
					String filename = file.getFileName().toString();
					String contentType = Files.probeContentType(file);

					if ((contentType != null && contentType.startsWith("video")) ||
							(filename.endsWith(".mkv") &&
									!filename.contains("sample") &&
									!filename.contains("Sample"))) {
						fileList.add(file);
					}
				}
			}
		}
	}
}
