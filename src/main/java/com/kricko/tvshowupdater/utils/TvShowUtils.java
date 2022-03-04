package com.kricko.tvshowupdater.utils;

import com.kricko.tvshowupdater.configuration.Config;
import com.kricko.tvshowupdater.configuration.TorrentConfig;
import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Item;
import com.kricko.tvshowupdater.torrent.QBitTorrent;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kricko.tvshowupdater.utils.Constants.FILE_MISSING;
import static com.kricko.tvshowupdater.utils.Constants.FILE_TIDY_UP;
import static java.lang.Thread.currentThread;

/**
 */
public class TvShowUtils {

	private static final List<String> tidyUpDirs = new ArrayList<>();
	
	/**
	 * Method removeDuplicateEpisodes.
	 * @param items List<Item>
	 * @param regex String
	 * @return List<Item>
	 */
	public static List<Item> removeDuplicateEpisodes(List<Item> items, String regex){
		Map<Integer,List<String>> shows = new HashMap<>();
		List<Item> newItems = new ArrayList<>();
		Pattern pattern = Pattern.compile(regex);

		for(Item item:items){
			Matcher itemMatcher = pattern.matcher(item.getRawTitle());

			if(!shows.containsKey(item.getShowId())){
				List<String> episodes = new ArrayList<>();

				while(itemMatcher.find()){
					newItems.add(item);
					episodes.add(itemMatcher.group());
					shows.put(item.getShowId(), episodes);
				}
			} else {
				List<String> episodes = shows.get(item.getShowId());
				while(itemMatcher.find()){
					if(!episodes.contains(itemMatcher.group())){
						episodes.add(itemMatcher.group());
						newItems.add(item);
					}
				}
				shows.put(item.getShowId(), episodes);
			}
		}

		return newItems;
	}

	/**
	 * Method downloadNewItems.
	 * @param item Item
	 * @param detail Details
	 * @throws Throwable
	 */
	public static boolean downloadNewItems(Config config, Item item, Details detail) throws Throwable{

		boolean newDownloads = false;
		String regex = config.getShowRegex().replaceAll("NAME", detail.getRegexName());

		Pattern pattern = Pattern.compile(regex);
		Matcher itemMatcher = pattern.matcher(item.getRawTitle());
		while(itemMatcher.find()){
			String nameAndEpisode = itemMatcher.group();
			int[] seasonAndEpisode = getSeasonAndEpisode(nameAndEpisode);

			int seasonInt = seasonAndEpisode[0];
			int episodeInt = seasonAndEpisode[1];

			Path dir = Paths.get(detail.getPath() + File.separatorChar + "Season " + seasonInt);

			if (!detail.getSkipSeason().contains(seasonInt)
					&& episodeDoesntExist(dir, seasonInt, episodeInt, detail.getIgnoreMissing())
					&& null != item.getLink()){
				System.out.printf("%s - %s doesn't exist so downloading%n", currentThread().getName(), nameAndEpisode);
				newDownloads = true;
				downloadMagnetLink(config, item.getLink(), dir);
			}
		}
		
		if(newDownloads){
			Thread.sleep(2000);
		}
		return newDownloads;
	}

	public static void downloadMagnetLink(Config config, String magnetUrl, Path dir) {
		TorrentConfig torrentConfig = config.getQBitTorrentConfig();
		QBitTorrent torrentClient = new QBitTorrent(torrentConfig);
		torrentClient.addNewTorrent(magnetUrl, dir);
		String sDir = dir.toString();
		if(!tidyUpDirs.contains(sDir)){
			tidyUpDirs.add(sDir);
		}
	}

	public static boolean episodeDoesntExist(Path dir, int seasonInt, int episodeInt, List<String> ignorable) {
		List<String> existingItems = getExistingItems(dir);

		String filePrefix = String.format("S%sE%s", formatIntToString(seasonInt), formatIntToString(episodeInt));
		return !episodeExists(existingItems, filePrefix, ignorable);
	}
	
	public static void appendDirToTidyUpList(){
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_TIDY_UP, false)));
		    for(String dir:tidyUpDirs){
		    	out.println(dir);
		    }
		    out.flush();
		    out.close();
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}

	public static void writeMissingEpisodesToFile(List<String> missingEpisodes) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_MISSING, true)));
			for(String episodes:missingEpisodes){
				out.println(episodes);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			System.err.println("Failed to write to the file " + FILE_MISSING);
		}
	}
	
	/**
	 * Method getListOfTidyUpDirs.
	 * @return List<String>
	 */
	public static List<String> getListOfTidyUpDirs(){
		List<String> directories = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FILE_TIDY_UP));
			String line;
			while((line = reader.readLine()) != null){
				directories.add(line);
			}
			reader.close();
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
		
		return directories;
	}
	
	/**
	 * Method getEpisodeIds.
	 * @param value String
	 * @return int[]
	 */
	public static int[] getEpisodeIds(String value, String splitChar, int startIndex){
		int[] items = new int[2];
		items[0] = Integer.parseInt(value.substring(startIndex, value.toUpperCase().indexOf(splitChar)));
		items[1] = Integer.parseInt(value.substring(value.toUpperCase().indexOf(splitChar) + 1));
		
		return items;
	}
	
	/**
	 * Method buildFileName.
	 * @param ep Episode
	 * @return String
	 */
	public static String buildFileName(Episode ep){
		String filename = null;
		if(ep.getEpisodeName() != null){
			filename = "S"+formatIntToString(ep.getSeasonNumber())
					+ "E"+formatIntToString(ep.getEpisodeNumber()) 
					+ " - " + replaceSpecialChars(ep.getEpisodeName());
		}
		
		return filename;
	}

	/**
	 * Method episodeExists.
	 * @param items List<String>
	 * @param episodeName String
	 * @return boolean
	 */
	private static boolean episodeExists(List<String> items, String episodeName, List<String> ignorable){
		if (!items.isEmpty()){
			for (String item:items){
				if (item.startsWith(episodeName) || ignorable.contains(episodeName)) return true;
			}
		}
		return false;
	}

	/**
	 * Method formatIntToString.
	 * @param value int
	 * @return String
	 */
	private static String formatIntToString(int value){
		String str = ""+value;
		if(value < 10){
			str = "0" + value;
		}
		return str;
	}

	/**
	 * Method getExistingItems.
	 * @param dir Path
	 * @return List<String>
	 */
	private static List<String> getExistingItems(Path dir) {
		List<String> result = new ArrayList<>();
		if (!Files.exists(dir)) {
			try {
				Files.createDirectories(dir);
			} catch (IOException ex) {
				System.out.printf("Directory %s doesn't exist and failed to create%n", dir);
			}
		}
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{mp4,avi,mkv}")) {
			for (Path entry: stream) {
				result.add(entry.getFileName().toString());
			}
		} catch (DirectoryIteratorException | IOException ex) {
			System.err.println("Failed to get list of existing items in " + dir);
		}

		return result;
	}
	
	/**
	 * Method replaceSpecialChars.
	 * @param value String
	 * @return String
	 */
	private static String replaceSpecialChars(String value){
		return value.replace(":", "").replace("?","")
				.replace("\\"," ").replace("/"," ");
	}
	
	/**
	 * Method getSeasonAndEpisode.
	 * @param title String
	 * @return int[]
	 */
	private static int[] getSeasonAndEpisode(String title){
		Pattern pattern = Pattern.compile("(^|)S([0-9]+)E([0-9]+)");
		Matcher itemMatcher = pattern.matcher(title);
		while(itemMatcher.find()){
			String se = itemMatcher.group();
			return getEpisodeIds(se, "E", 1);
		}
		Pattern pattern2 = Pattern.compile("(^|)[0-9]+x[0-9]+");
		Matcher itemMatcher2 = pattern2.matcher(title);
		while(itemMatcher2.find()){
			String se = itemMatcher2.group();
			return getEpisodeIds(se, "X", 0);
		}
		
		return null;
	}
}
