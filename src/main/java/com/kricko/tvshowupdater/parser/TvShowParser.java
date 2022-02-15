package com.kricko.tvshowupdater.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.kricko.tvshowupdater.model.Shows;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 */
public class TvShowParser {

	/**
	 * Method parseShows.
	 * @return Shows
	 * @throws IOException
	 * @throws ParseException
	 */
	public Shows parseShows() throws IOException, URISyntaxException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());
    	FileReader reader = new FileReader(new File(Objects.requireNonNull(getClass().getClassLoader().getResource("tvshows.json")).toURI()));
		return mapper.readValue(reader, Shows.class);
	}
}
