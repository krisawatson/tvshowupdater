package com.kricko.tvshowupdater.utils;

/**
 */
public interface Constants {

	public final String FILE_TIDY_UP = "tidyuplist.txt";
	public final String SETTING_PROP = "setting";
	public final String SETTING_UPDATE_BEFORE_DOWNLOAD = SETTING_PROP + ".update_before_download";
	public final String SETTING_XBMC_UPDATE = SETTING_PROP + ".xbmc_update";
	public final String FILE_MISSING_EPISODES = "missing_episodes.txt";
	
	// For KAT search engine
	public final String KAT_URL = "http://kickass.to";
	public final String KAT_SEARCH = "/usearch/";
	// For The Pirate Bay search engine
	public final String TPB_URL = "http://thepiratebay.se";
	public final String TPB_SEARCH = "/search/";
	
	/*
	 * The TV DB values
	 */
	public final String API_KEY = "2805AD2873519EC5";
	public final String LANGUAGE = "en";
	
	// REGEX Expressions
	public final String REGEX_SERIES_EPISODE = "(^|)[sS]([0-9]+)[eE]([0-9]+)";
	public final String REGEX_SERIES_EPISODE2 = "(^|)([0-9]+)[xX]([0-9]+)";
	
	/*
	 * List of config properties constants
	 */
	public final String UTORRENT = "utorrent";
}
