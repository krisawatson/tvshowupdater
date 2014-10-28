package com.kricko.tvshowupdater.utorrent;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.kricko.tvshowupdater.utils.Config;
import com.kricko.tvshowupdater.utils.Constants;
import com.kricko.tvshowupdater.utils.HttpUtils;

/**
 */
public class UTorrent {

	private String host, port, username, password;
	private String token;
	
	public UTorrent(){
		Config props = Config.getInstance();
		host = props.getProperty(Constants.UTORRENT + ".webhost");
		port = props.getProperty(Constants.UTORRENT + ".webport");
		username = props.getProperty(Constants.UTORRENT + ".webuser");
		password = props.getProperty(Constants.UTORRENT + ".webpass");
	}
	
	/**
	 * Method getToken.
	 * @throws IOException
	 */
	public void getToken() throws IOException{
		String response = HttpUtils.get("http://"+host+":"+port + "/gui/token.html", null, username+":"+password);
		
		Document doc = Jsoup.parse(response);
		Element tokenElem = doc.getElementById("token");
		token = tokenElem.text();
	}
	
	/**
	 * Method getListOfTorrents.
	 * @throws IOException
	 */
	public void getListOfTorrents() throws IOException{
		String data = "?token="+token;
		String response = HttpUtils.get("http://"+host+":"+port + "/gui/", data, username+":"+password);
		
		System.out.println(response);
	}
}
