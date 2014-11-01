package com.kricko.tvshowupdater.utorrent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.kricko.tvshowupdater.model.Torrent;
import com.kricko.tvshowupdater.utils.Config;
import com.kricko.tvshowupdater.utils.Constants;

/**
 */
public class UTorrent {

	private String host, port, username, password;
	private String token;
	private static Map<String, String> cookies;

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
		try {
			String login = username + ":" + password;
			String base64login = new String(Base64.encodeBase64(login.getBytes()));

			Response tokenData = Jsoup.connect("http://"+host+":"+port + "/gui/token.html")
					.method(Connection.Method.GET)
					.header("Authorization", "Basic " + base64login)
					.timeout(0)
					.execute();

			cookies = tokenData.cookies();

			Document doc = Jsoup.parse(tokenData.body());
			Element tokenElem = doc.getElementById("token");
			token = tokenElem.text();
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

	/**
	 * Method getListOfTorrents.
	 * @throws IOException
	 * @throws ParseException 
	 */
	public Torrent getListOfTorrents() throws IOException, ParseException{
		String login = username + ":" + password;
		String base64login = new String(Base64.encodeBase64(login.getBytes()));

		Response response = Jsoup.connect("http://"+host+":"+port + "/gui/?token="+token+"&list=1")
				.method(Connection.Method.GET)
				.cookies(cookies)
				.header("Authorization", "Basic " + base64login)
				.timeout(0)
				.execute();

		ObjectMapper mapper = new ObjectMapper();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(response.body());

		Torrent torrent = mapper.readValue(jsonObject.toString(), Torrent.class);
		return torrent;
	}

	public List<String> getFinishedHashes(Torrent torrentList) throws IOException, ParseException {

		List<String> hashes = new ArrayList<String>();

		for(List<Object> torrents:torrentList.getTorrents()){
			if("Seeding".equals(torrents.get(21))){
				hashes.add(torrents.get(0).toString());
			}
		}

		for(String hash:hashes){
			System.out.println("Torrent hash that is completed " + hash);
		}

		return hashes;
	}

	public void removeCompletedTorrents(List<String> hashes) throws IOException {
		String login = username + ":" + password;
		String base64login = new String(Base64.encodeBase64(login.getBytes()));

		String url = "http://"+host+":"+port + "/gui/?token="+token;

		for(String hash:hashes){
			Response response = Jsoup.connect(url + "&action=remove&hash=" + hash)
					.method(Connection.Method.POST)
					.cookies(cookies)
					.header("Authorization", "Basic " + base64login)
					.timeout(0)
					.execute();
			
			System.out.println(response.body());
		}
	}
}
