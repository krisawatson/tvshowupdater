package com.kricko.tvshowupdater.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.kricko.tvshowupdater.model.Shows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

/**
 */
public class TvShowParser {

	/**
	 * Method parseShows.
	 * @return Shows
	 * @throws IOException
	 * @throws ParseException
	 */
	public Shows parseShows() throws IOException, ParseException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());
    	FileReader reader = new FileReader(Paths.get("tvshows.json").toString());
    	JSONParser jsonParser = new JSONParser();
    	JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

    	Shows shows = mapper.readValue(jsonObject.toString(), Shows.class);
    	
    	return shows;
	}
}
