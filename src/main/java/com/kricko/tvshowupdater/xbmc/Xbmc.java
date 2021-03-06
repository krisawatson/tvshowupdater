package com.kricko.tvshowupdater.xbmc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;

import com.kricko.tvshowupdater.utils.Config;

/**
 */
public class Xbmc {
	
	static Config p;
	static{
		p = Config.getInstance();
	}
	
	/**
	 * Update list of servers videos
	 * @param hosts
	
	
	 * @throws IOException * @throws HttpException */
	public static void updateHostVideos(String[] hosts) throws IOException, HttpException{
		for(String host:hosts){
			updateHostVideos(host);
    	}
	}
	
	/**
	 * Update a single server's video's
	 * @param host
	
	
	 * @throws IOException * @throws HttpException */
	public static void updateHostVideos(String host) throws IOException, HttpException{
		XbmcJsonRpc rpc = new XbmcJsonRpc(p.getProperty("xbmc."+host+".address"), 
				p.getIntProperty("xbmc."+host+".tcpport"), false, 
				p.getIntProperty("xbmc."+host+".retries"),
				p.getProperty("xbmc."+host+".user"),
				p.getProperty("xbmc."+host+".pass"));
		rpc.updateVideoLibrary();
	}
	
	/**
	 * Clean a list of server's video library
	 * @param hosts
	
	
	 * @throws IOException * @throws HttpException */
	public static void cleanVideoLibrary(String[] hosts) throws IOException, HttpException{
		for(String host:hosts){
			cleanVideoLibrary(host);
    	}
	}
	
	/**
	 * Clean a single server video library
	 * @param host
	
	
	 * @throws IOException * @throws HttpException */
	public static void cleanVideoLibrary(String host) throws IOException, HttpException{
			XbmcJsonRpc rpc = new XbmcJsonRpc(p.getProperty("xbmc."+host+".address"), 
    				p.getIntProperty("xbmc."+host+".tcpport"), false, 
    				p.getIntProperty("xbmc."+host+".retries"),
    				p.getProperty("xbmc."+host+".user"),
    				p.getProperty("xbmc."+host+".pass"));
    		rpc.cleanVideoLibrary();
	}
	
	/**
	 * Clean a list of server's video library
	 * @param hosts
	
	
	 * @throws IOException * @throws HttpException */
	public static void executeTraktAddon(String[] hosts) throws IOException, HttpException{
		for(String host:hosts){
			executeTraktAddon(host);
    	}
	}
	
	/**
	 * Clean a single server video library
	 * @param host
	
	
	 * @throws IOException * @throws HttpException */
	public static void executeTraktAddon(String host) throws IOException, HttpException{
			XbmcJsonRpc rpc = new XbmcJsonRpc(p.getProperty("xbmc."+host+".address"), 
    				p.getIntProperty("xbmc."+host+".tcpport"), false, 
    				p.getIntProperty("xbmc."+host+".retries"),
    				p.getProperty("xbmc."+host+".user"),
    				p.getProperty("xbmc."+host+".pass"));
			
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("addonid", "script.trakt");
			
    		rpc.executeAddon(params);
	}
}
