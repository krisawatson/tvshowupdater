package com.kricko.tvshowupdater.utils;

/**
 */
public interface Constants {

	String FILE_TIDY_UP = "tidyuplist.txt";
	String FILE_MISSING = "missing.txt";
	
	/*
	 * The TV DB values
	 */
	String API_KEY = "2805AD2873519EC5";
	String LANGUAGE = "en";
	
	// REGEX Expressions
	String REGEX_SERIES_EPISODE = "(^|)[sS]([0-9]+)[eE]([0-9]+)";
	String REGEX_SERIES_EPISODE2 = "(^|)([0-9]+)[xX]([0-9]+)";
	String REGEX_SEASON = "(^Season [0-9]+$)";
	
	/*
	 * List of config properties constants
	 */
	String UTORRENT = "utorrent";
}
