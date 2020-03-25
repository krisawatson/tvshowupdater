package com.kricko.tvshowupdater.model;

import java.util.List;
import java.util.Optional;


/**
 */
public class Details {

	private String name;
	
	private String rssfeed;
	
	private String regex;
	
	private String path;
	
	private List<String> skip;

	private Optional<String> tvdbSeriesId;

	/**
	 * Method getName.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method setName.
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method getRssfeed.
	 * @return String
	 */
	public String getRssfeed() {
		return rssfeed;
	}

	/**
	 * Method setRssfeed.
	 * @param rssfeed String
	 */
	public void setRssfeed(String rssfeed) {
		this.rssfeed = rssfeed;
	}

	/**
	 * Method getRegex.
	 * @return String
	 */
	public String getRegex() {
		return regex;
	}

	/**
	 * Method setRegex.
	 * @param regex String
	 */
	public void setRegex(String regex) {
		this.regex = regex;
	}

	/**
	 * Method getPath.
	 * @return String
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Method setPath.
	 * @param path String
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Method getSkip
	 * @return List<String>
	 */
	public List<String> getSkip() {
		return skip;
	}

	/**
	 * Method setSkip
	 * @param skip List<String>
	 */
	public void setSkip(List<String> skip) {
		this.skip = skip;
	}

	public Optional<String> getTvdbSeriesId() {
		return tvdbSeriesId;
	}

	public void setTvdbSeriesId(Optional<String> tvdbSeriesId) {
		this.tvdbSeriesId = tvdbSeriesId;
	}
}
