package com.kricko.tvshowupdater.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.parser.ParseException;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Item;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.parser.TvShowParser;

public class TvShowUtils {

	private static List<String> tidyUpDirs = new ArrayList<String>();
	
	public static List<Item> removeDuplicateEpisodes(List<Item> items, String regex){
		Map<Integer,List<String>> shows = new HashMap<Integer,List<String>>();
		List<Item> newItems = new ArrayList<Item>();
		Pattern pattern = Pattern.compile(regex);

		for(Item item:items){
			Matcher itemMatcher = pattern.matcher(item.getRawTitle());

			if(!shows.containsKey(item.getShowId())){
				List<String> episodes = new ArrayList<String>();

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

	public static void downloadNewItems(Item item, Details detail) throws Throwable{

		Pattern pattern = Pattern.compile(detail.getRegex());
		Matcher itemMatcher = pattern.matcher(item.getRawTitle());
		while(itemMatcher.find()){
			String nameAndEpisode = itemMatcher.group();
			int[] seasonAndEpisode = getSeasonAndEpisode(nameAndEpisode);

			int seasonInt = seasonAndEpisode[0];
			int episodeInt = seasonAndEpisode[1];
			
			Path dir = Paths.get(detail.getPath() + File.separatorChar + "Season " + seasonInt);
			List<String> existingItems = getExistingItems(dir);
			System.out.println("The directory is " + dir);

			String filePrefix = String.format("S%sE%s", formatIntToString(seasonInt), formatIntToString(episodeInt));

			if(episodeExists(existingItems, filePrefix)){
				System.out.println(filePrefix + " episode already exists");
			} else {
				Properties prop = TvShowProperties.getInstance().getProperties();
				String[] params = {prop.getProperty("torrent.client"),"/DIRECTORY", "\""+dir+"\"", "\""+item.getLink()+"\"" };
				System.out.println("Executing command: utorrent.exe /DIRECTORY \""+dir+"\" \""+item.getLink()+"\"");
				
				String sDir = dir.toString();
				if(!tidyUpDirs.contains(sDir)){
					tidyUpDirs.add(sDir);
				}
				
				Runtime.getRuntime().exec(params);
			}
		}
	}

	private static boolean episodeExists(List<String> items, String episodeName){
		if(!items.isEmpty()){
			for(String item:items){
				if(item.contains(episodeName)) return true;
			}
		}

		return false;
	}

	private static String formatIntToString(int value){
		String str = ""+value;
		if(value < 10){
			str = "0" + value;
		}
		return str;
	}

	private static List<String> getExistingItems(Path dir) throws Throwable{
		List<String> result = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{mp4,avi,mkv}")) {
			for (Path entry: stream) {
				result.add(entry.getFileName().toString());
			}
		} catch (NoSuchFileException nsfe){
			// Nothing to do here
		}catch (DirectoryIteratorException | IOException ex) {
			// I/O error encounted during the iteration, the cause is an IOException
			throw ex.getCause();
		} 
		
		return result;
	}
	
	public static void appendDirToTidyUpList(){
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Constants.FILE_TIDY_UP_DIR, false)));
		    for(String dir:tidyUpDirs){
		    	out.println(dir);
		    }
		    out.flush();
		    out.close();
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}
	
	public static List<String> getListOfTidyUpDirs(){
		List<String> directories = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Constants.FILE_TIDY_UP_DIR));
			String line = null;
			while((line = reader.readLine()) != null){
				directories.add(line);
			}
			reader.close();
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
		
		return directories;
	}
	
	public static Shows getListOfShows() throws IOException, ParseException{
		TvShowParser parser = new TvShowParser();
        return parser.parseShows(); 
	}
	
	public static boolean valid(String s)
	{
		return s != null && !s.trim().isEmpty();
	}
	
	public static int[] getEpisodeIds(String value){
		int[] items = new int[2];
		items[0] = Integer.parseInt(value.substring(1, value.indexOf("E")));
		items[1] = Integer.parseInt(value.substring(value.indexOf("E") + 1));
		
		return items;
	}
	
	public static String buildFileName(Episode ep){
		String filename = "S"+formatIntToString(ep.getSeasonNumber())
				+ "E"+formatIntToString(ep.getEpisodeNumber()) 
				+ " - " + replaceSpecialChars(ep.getEpisodeName());
		
		return filename;
	}
	
	public static String replaceSpecialChars(String value){
		return value.replace(":", "").replace("?","");
	}
	
	private static int[] getSeasonAndEpisode(String title){
		Pattern pattern = Pattern.compile("(^|)S([0-9]+)E([0-9]+)");
		Matcher itemMatcher = pattern.matcher(title);
		while(itemMatcher.find()){
			String se = itemMatcher.group();
			return getEpisodeIds(se);
		}
		
		return null;
	}
}
