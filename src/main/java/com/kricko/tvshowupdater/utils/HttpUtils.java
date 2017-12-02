package com.kricko.tvshowupdater.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 */
public class HttpUtils {

	/*
	 * Returns null if fail, otherwise returns the String response from the post
	 */
	/**
	 * Method post.
	 * @param strUrl String
	 * @param data String
	 * @param authString String
	 * @return String
	 * @throws Exception
	 */
	public static String post(String strUrl, String data, String authString) throws Exception{
		return makeHttpCall("POST", strUrl, data, authString);
	}
	
	/**
	 * Method get.
	 * @param strUrl String
	 * @param data String
	 * @param authString String
	 * @return String
	 * @throws IOException
	 */
	public static String get(String strUrl, String data, String authString) throws IOException{
		return makeHttpCall("GET", strUrl, data, authString);
	}
	
	/**
	 * Method makeHttpCall.
	 * @param method String
	 * @param strUrl String
	 * @param data String
	 * @param authString String
	 * @return String
	 * @throws IOException
	 */
	@SuppressWarnings("restriction")
	private static String makeHttpCall(String method, String strUrl, String data, String authString) 
			throws IOException{
		URL url = new URL(strUrl);

		final String host = url.getHost();
		final String contentType = "application/x-www-form-urlencoded";
		int contentLength = 0;
		if(data != null) {
			contentLength = getContentLength(data);
		}
		final String encoding = "UTF-8";//good idea?        
		final String connection = "Close";// "keep-alive";

		System.out.println("Sending data to: "+ url+" (host="+host+", encoding="+encoding+", method="+method+", Content-Type="+contentType+", Content-Length="+contentLength+", Connection="+connection+"):"
				+"\r\n"+data);        

		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);//let it know we are writing data  
		conn.setRequestMethod(method);//POST
		conn.setRequestProperty("host", host);        
		conn.setRequestProperty("content-type", contentType);
		conn.setRequestProperty("Content-Encoding", encoding);
		conn.setRequestProperty("content-length", contentLength+"");                                      
		conn.setRequestProperty("connection", connection);                

		//authenticate if used
		if(TvShowUtils.valid(authString)){
			String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
			conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
		}

		conn.setReadTimeout((int) (60*1000));

		//send data the to remote server
		if(data != null){
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(data);
			writer.flush();
			writer.close();
		}

		int responseCode = 400;
		try{
			responseCode = conn.getResponseCode();
		}catch(Exception x){
			System.err.println("Failed to get response code from HTTP Server. Check your URL and username/password.\n"+x.getLocalizedMessage());
		}

		//read the response                
		String response = readStream(responseCode == 200 ? conn.getInputStream() : conn.getErrorStream());        
		if(response == null){
			return null;
		}

		System.out.println("Raw response from "+method+". Response Code = "+conn.getResponseCode()+" ("+conn.getResponseMessage()+"):\r\n"+ response);
		return response.toString();
	}
	
	/**
	 * Method getContentLength.
	 * @param data String
	 * @return int
	 * @throws UnsupportedEncodingException
	 */
	private static int getContentLength(String data) throws UnsupportedEncodingException{        
		ByteArrayOutputStream sizeArray = new ByteArrayOutputStream();
		PrintWriter sizeGetter = new PrintWriter(new OutputStreamWriter(sizeArray, "UTF-8" ));                
		sizeGetter.write(data);
		sizeGetter.flush();
		sizeGetter.close();        
		return sizeArray.size();        
	}
	
	/**
	 * Method readStream.
	 * @param is InputStream
	 * @return String
	 */
	private static String readStream(InputStream is)
	{
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			//return the raw data as a string
			StringBuilder data = new StringBuilder();
			while(true)
			{
				int i = in.read();
				if(i == -1)break;
				data.append( (char) i);//convert int to char
			}    
			return data.toString();
		}
		catch(IOException x){
			System.out.println("Failed to read stream: "+ x.getLocalizedMessage());
			return null;
		}
	}
	
	/**
	 * Method setProxy.
	 * @param host String
	 * @param port String
	 */
	public static void setProxy(String host, String port){
		System.setProperty("http.proxyHost", host);
		System.setProperty("http.proxyPort", port);
	}
	
	public static void clearProxy(){
		System.setProperty("http.proxyHost", "");
		System.setProperty("http.proxyPort", "");
	}
}
