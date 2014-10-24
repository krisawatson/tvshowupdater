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
	
	/**
	 * Update list of servers videos
	 * @param hosts
	 * @throws IOException
	 * @throws HttpException
	 */
	public static void updateHostVideos(String[] hosts) throws IOException, HttpException{
		for(String host:hosts){
			updateHostVideos(host);
    	}
	}
	
	/**
	 * Update a single server's video's
	 * @param host
	 * @throws IOException
	 * @throws HttpException
	 */
	public static void updateHostVideos(String host) throws IOException, HttpException{
		String hostName = prop.getProperty("xbmc."+host+".address");
		String tcpPort = prop.getProperty("xbmc."+host+".tcpport");
		
		int port = Integer.parseInt(tcpPort);
		
		XbmcJsonRpc rpc = new XbmcJsonRpc(hostName, port, false);
		rpc.updateVideoLibrary();
	}
	
	/**
	 * Clean a list of server's video library
	 * @param hosts
	 * @throws IOException
	 * @throws HttpException
	 */
	public static void cleanVideoLibrary(String[] hosts) throws IOException, HttpException{
		for(String host:hosts){
			cleanVideoLibrary(host);
    	}
	}
	
	/**
	 * Clean a single server video library
	 * @param host
	 * @throws IOException
	 * @throws HttpException
	 */
	public static void cleanVideoLibrary(String host) throws IOException, HttpException{
			String hostName = prop.getProperty("xbmc."+host+".address");
    		String tcpPort = prop.getProperty("xbmc."+host+".tcpport");
    		
    		int port = Integer.parseInt(tcpPort);
    		
    		XbmcJsonRpc rpc = new XbmcJsonRpc(hostName, port, false);
    		rpc.cleanVideoLibrary();
	}
}
