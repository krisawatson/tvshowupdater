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

public class HttpUtils {

	/*
	 * Returns null if fail, otherwise returns the String response from the post
	 */
	public static String post(String strUrl, String data, String authString) throws Exception{
		return makeHttpCall("POST", strUrl, data, authString);
	}
	
	public static String get(String strUrl, String data, String authString) throws IOException{
		return makeHttpCall("GET", strUrl, data, authString);
	}
	
	@SuppressWarnings("restriction")
	private static String makeHttpCall(String method, String strUrl, String data, String authString) 
			throws IOException{
		URL url = new URL(strUrl);

		final String host = url.getHost();
		final String contentType = "application/x-www-form-urlencoded";
		final int contentLength = getContentLength(data);
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
			String authStringEnc = new sun.misc.BASE64Encoder().encode(authString.getBytes());
			conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
		}

		conn.setReadTimeout((int) (60*1000));

		//send data the to remote server
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		writer.write(data);
		writer.flush();
		writer.close();

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
	
	private static int getContentLength(String data) throws UnsupportedEncodingException{        
		ByteArrayOutputStream sizeArray = new ByteArrayOutputStream();
		PrintWriter sizeGetter = new PrintWriter(new OutputStreamWriter(sizeArray, "UTF-8" ));                
		sizeGetter.write(data);
		sizeGetter.flush();
		sizeGetter.close();        
		return sizeArray.size();        
	}
	
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
	
	public static void setProxy(String host, String port){
		System.setProperty("http.proxyHost", host);
		System.setProperty("http.proxyPort", port);
	}
	
	public static void clearProxy(){
		System.setProperty("http.proxyHost", "");
		System.setProperty("http.proxyPort", "");
	}
}
