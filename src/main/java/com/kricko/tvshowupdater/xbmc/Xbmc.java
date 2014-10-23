package com.kricko.tvshowupdater.xbmc;

import java.util.Properties;

import com.kricko.tvshowupdater.utils.TvShowProperties;

public class Xbmc {

	static {
		loadProperties();
	}
	static Properties prop;
	private static void loadProperties() {
		prop = TvShowProperties.getInstance().getProperties();
	}
	
	public static void updateHosts(){
		String[] hosts = prop.getProperty("xbmc.host_list").split(",");
    	for(String host:hosts){
    		System.out.println("XBMC " + host + " " + prop.getProperty("xbmc."+host));
    	}
	}
}
