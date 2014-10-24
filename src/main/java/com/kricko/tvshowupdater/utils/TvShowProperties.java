package com.kricko.tvshowupdater.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TvShowProperties {
	
	private static TvShowProperties instance = null;
	private static Properties prop = null;
	protected TvShowProperties() {
		prop = new Properties();
		InputStream in = TvShowProperties.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			prop.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static TvShowProperties getInstance() {
		if(instance == null) {
			instance = new TvShowProperties();
		}
		return instance;
	}
	
	public Properties getProperties(){
		return prop;
	}
	
	public String getProperty(String propertyName){
		return prop.getProperty(propertyName);
	}
	
	private boolean getBooleanProperty(String propertyName){
		String property = getProperty(propertyName);
		if(null != property){
			return Boolean.parseBoolean(property);
		}
		return false;
	}
	
	public boolean updateBeforeDownload(){
		return getBooleanProperty(Constants.SETTING_UPDATE_BEFORE_DOWNLOAD);
	}
}
