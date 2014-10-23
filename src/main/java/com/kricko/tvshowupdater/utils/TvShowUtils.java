package com.kricko.tvshowupdater.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kricko.tvshowupdater.objects.Item;

public class TvShowUtils {

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
}
