package com.kricko.tvshowupdater.xbmc;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.httpclient.HttpException;

import com.kricko.tvshowupdater.utils.TvShowProperties;

public class Xbmc {

	static {
		loadProperties();
	}
	static Properties prop;
	private static void loadProperties() {
		prop = TvShowProperties.getInstance().getProperties();
	}
	
	public static void updateHostVideos() throws IOException, HttpException{
		String[] hosts = prop.getProperty("xbmc.host_list").split(",");
    	for(String host:hosts){
    		String hostName = prop.getProperty("xbmc."+host+".address");
    		String tcpPort = prop.getProperty("xbmc."+host+".tcpport");
    		
    		int port = Integer.parseInt(tcpPort);
    		
    		XbmcJsonRpc rpc = new XbmcJsonRpc(hostName, port, false);
    		rpc.updateVideoLibrary();
    	}
	}
}
