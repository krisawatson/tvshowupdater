package com.kricko.tvshowupdater.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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

import com.kricko.tvshowupdater.constants.TvShowConst;
import com.kricko.tvshowupdater.objects.Details;
import com.kricko.tvshowupdater.objects.Item;

public class TvShowUtils {

	private static List<String> tidyUpDirs = new ArrayList<String>();
	
	public static List<Item> removeDuplicateEpisodes(List<Item> items, String regex){
		Map<Integer,List<String>> shows = new HashMap<Integer,List<String>>();
		List<Item> newItems = new ArrayList<Item>();
		Pattern pattern = Pattern.compile(regex);

		for(Item item:items){
			Matcher itemMatcher = pattern.matcher(item.getTitle());

			if(!shows.containsKey(item.getShowId())){
				newItems.add(item);
				List<String> episodes = new ArrayList<String>();

				while(itemMatcher.find()){
					episodes.add(itemMatcher.group());
				}
				shows.put(item.getShowId(), episodes);
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
		Matcher itemMatcher = pattern.matcher(item.getTitle());
		while(itemMatcher.find()){
			String nameAndEpisode = itemMatcher.group();
			String[] nameList = nameAndEpisode.split(" ");
			String episodeDetails = nameList[nameList.length - 1];

			String[] seasonAndEpisode = episodeDetails.split("x");

			int seasonInt = 0, episodeInt = 0;
			try{
				seasonInt = Integer.parseInt(seasonAndEpisode[0]);
				episodeInt = Integer.parseInt(seasonAndEpisode[1]);
			} catch (NumberFormatException nfe){

			}

			Path dir = Paths.get(detail.getPath() + File.separatorChar + "Season " + seasonAndEpisode[0]);
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
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(TvShowConst.FILE_TIDY_UP_DIR, true)));
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
			BufferedReader reader = new BufferedReader(new FileReader(TvShowConst.FILE_TIDY_UP_DIR));
			String line = null;
			while((line = reader.readLine()) != null){
				directories.add(line);
			}
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
		
		return directories;
	}
}
