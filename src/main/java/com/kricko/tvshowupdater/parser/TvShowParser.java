package com.kricko.tvshowupdater.parser;

import java.io.FileReader;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.kricko.tvshowupdater.objects.Shows;

public class TvShowParser {

	public Shows parseShows() throws IOException, ParseException{
		ClassLoader classLoader = this.getClass().getClassLoader();
    	
    	ObjectMapper mapper = new ObjectMapper();
    	FileReader reader = new FileReader(classLoader.getResource("tvshows.json").getFile());
    	JSONParser jsonParser = new JSONParser();
    	JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

    	Shows shows = mapper.readValue(jsonObject.toString(), Shows.class);
    	
    	return shows;
	}
}
