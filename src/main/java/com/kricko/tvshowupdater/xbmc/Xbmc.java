package com.kricko.tvshowupdater.xbmc;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import com.kricko.tvshowupdater.utils.TvShowProperties;

public class Xbmc {

	static {
		loadProperties();
	}
	static Properties prop;
	private static void loadProperties() {
		prop = TvShowProperties.getInstance().getProperties();
	}
	
	public static void updateHosts() throws IOException, HttpException{
		HttpClient client = new HttpClient();

		String[] hosts = prop.getProperty("xbmc.host_list").split(",");
    	for(String host:hosts){
    		String hostName = prop.getProperty("xbmc."+host+".address");
    		String hostPort = prop.getProperty("xbmc."+host+".port");
    		
    		URL url = new URL("http://"+hostName+":"+hostPort);
    		GetMethod method = new GetMethod(url.toString());
    		client.startSession(url);
    		int statusCode = client.executeMethod(method);
    		
    		System.out.println("Status code is " + statusCode);
    	}
	}
}
