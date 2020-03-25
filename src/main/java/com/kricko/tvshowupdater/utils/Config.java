package com.kricko.tvshowupdater.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

/**
 */
public class Config {
	
	private static Config instance = null;
	private static Properties prop = null;
	protected Config() {
		prop = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(Paths.get("config.properties").toString());
			prop.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method getInstance.
	 * @return Config
	 */
	public static Config getInstance() {
		if(instance == null) {
			instance = new Config();
		}
		return instance;
	}
	
	/**
	 * Method getProperties.
	 * @return Properties
	 */
	public Properties getProperties(){
		return prop;
	}
	
	/**
	 * Method getProperty.
	 * @param propertyName String
	 * @return String
	 */
	public String getProperty(String propertyName){
		return prop.getProperty(propertyName);
	}
	
	/**
	 * Method getIntProperty.
	 * @param propertyName String
	 * @return Integer
	 */
	public Integer getIntProperty(String propertyName){
		String property = getProperty(propertyName);
		if(null != property){
			return Integer.parseInt(property);
		}
		return null;
	}
	
	/**
	 * Method getBooleanProperty.
	 * @param propertyName String
	 * @return boolean
	 */
	public boolean getBooleanProperty(String propertyName){
		String property = getProperty(propertyName);
		if(null != property){
			return Boolean.parseBoolean(property);
		}
		return false;
	}
	
	/**
	 * Method updateBeforeDownload.
	 * @return boolean
	 */
	public boolean updateBeforeDownload(){
		return getBooleanProperty(Constants.SETTING_UPDATE_BEFORE_DOWNLOAD);
	}

	/**
	 * Method isXbmcUpdate.
	 * @return boolean
	 */
	public boolean isXbmcUpdate(){
		return getBooleanProperty(Constants.SETTING_XBMC_UPDATE);
	}
}
