package com.kricko.tvshowupdater.xbmc;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class XbmcJsonRpc implements Runnable
{
	private boolean useHTTP=false, useRawTCP=true;
	private int port = 9090;
	private String server = "localhost";
	private long tcpReadTimeout = 50, tcpInitializeTimeout = 1000 * 60;//give XBMC x seconds to start returning data, then cancel the call
	private long lastRead = 0, tcpInit = 0;
	private boolean socketTimedOut = false, waitingForTimeout = false;;
	private Socket jsonRPCSocket = null;

	private final static String JSON_RPC_WEBSERVER_USERNAME = "xbmc";
	private final static String JSON_RPC_WEBSERVER_PASSWORD = null;
	
	public XbmcJsonRpc(String server, int port, boolean useHttp){
		this.server = server;
		this.port = port;
		this.useHTTP = useHttp;
		this.useRawTCP = !useHttp;
	}

	public JSONObject callMethod(String method, int id, Map<String,Object> params)
	{
		for(int i=0;i<3;i++)
		{
			switch(i)
			{
			case 0: break;
			case 1: tcpReadTimeout = tcpReadTimeout + 400; break;
			case 2: tcpReadTimeout = tcpReadTimeout + 1400; break;
			}

			JSONObject json = callMethodWithRetry(method, id, params);
			if(json != null)
			{
				if(i > 0)
					System.out.println("JSON was successfully retrieved after attempt #"+(i+1));
				return json;
			}
			else
			{
				System.out.println("JSON Returned was not valid (attempt " + (i + 1) + " of 3)" + (i == 2 ? "Ending retries, JSON is not valid" : ", will try again... "));
				if(i < 2)//if will try again, sleep a little bit
				{
					try{Thread.sleep((i+1) * 2500);}catch(Exception x){}
				}
			}
		}        
		return null;//reached end of tired and no valid json was returned
	}

	private JSONObject callMethodWithRetry(String method, int id, Map<String,Object> params)
	{
		String cmd = "{\"jsonrpc\": \"2.0\", ";
		cmd += "\"method\": \""+method+"\"";
		if(params != null && !params.isEmpty())
		{
			cmd += ", \"params\": {";
			for(Map.Entry<String,Object> entry : params.entrySet())
			{
				String key = entry.getKey();
				Object value = entry.getValue();
				cmd += jsonKeyValue(key, value)+", ";
			}
			cmd = cmd.substring(0, cmd.length()-", ".length());//trimm off the extra ", "
			cmd += "}";//end params
		}
		cmd += ", \"id\": \""+id+"\"}";

		System.out.println("Connecting to JSON-RPC and sending command: " + cmd);

		StringBuilder response = new StringBuilder();
		if(useHTTP)
		{
			//HTTP POST
			//CURL example: curl.exe -i -X POST -d "{\"jsonrpc\": \"2.0\", \"method\": \"JSONRPC.Version\", \"id\": 1}" http://localhost:8080/jsonrpc                        

			String rpcprefix = server+":"+port+"/jsonrpc";
			if(!rpcprefix.toLowerCase().startsWith("http"))
				rpcprefix = "http://"+rpcprefix;            
			try{
				String strResponse = post(rpcprefix, cmd);
				if(!valid(strResponse)) throw new Exception("No reponse or invalid response code.");
				response = new StringBuilder(strResponse);//save the response

			}catch(Exception x){
				System.err.println("Failed to POST to " + server + " " +x.getLocalizedMessage());
			}
		}

		//Config.log(INFO, "JSON-RPC Command = " + cmd);                
		if(useRawTCP)
		{
			try//send the command to the server
			{                
				jsonRPCSocket = new Socket(server, port);
				PrintWriter out = new PrintWriter(jsonRPCSocket.getOutputStream());                
				out.print(cmd);
				out.flush();                
				tcpInit = System.currentTimeMillis();
			}
			catch(Exception x)
			{
				System.err.println("Failed to call " + method +" using XBMC's JSON-RPC interface: "+x.getLocalizedMessage());
				return null;
			}

			try//read the result
			{
				System.out.println("Reading response from XBMC");
				BufferedReader in = new BufferedReader(new InputStreamReader(jsonRPCSocket.getInputStream()));                
				Thread timeout = new Thread(this);
				timeout.start();
				while(true)
				{
					char c;
					try
					{                        
						c = (char) in.read();
						lastRead = System.currentTimeMillis();
						if(c == -1) break;
					}
					catch(SocketException x)
					{
						if(!socketTimedOut)//expect an error on timeout
						System.err.println("Exception in JSON-RPC interface: "+ x.getLocalizedMessage());
						break;
					}
					response.append(c);
					//if(response.length() % 1000 == 0) Config.log(DEBUG, "Response length = "+ response.length() + response.substring(response.length()-500, response.length()));
				}
				if(!socketTimedOut && jsonRPCSocket != null && !jsonRPCSocket.isClosed()) jsonRPCSocket.close();                
				while(waitingForTimeout){}

			}
			catch(Exception x)
			{
				System.err.println("Error while reading response (TCP "+port+") from XBMC. Command="+cmd + "\n" + x.getLocalizedMessage());
				return null;
			}
		}

		System.out.println("Response from "+cmd+" \r\n"+response);//{   "id" : "1",   "jsonrpc" : "2.0",   "result" : "OK"}

		try
		{
			JSONObject obj = new JSONObject(response.toString().trim());

			return obj;
		}
		catch(Exception x)
		{
			System.err.println("The response from XBMC is not a valid JSON string:\n"+response);
			return null;
		}
	}

	private static String jsonKeyValue(String key, Object value)
	{
		try{
			String json = new JSONStringer().object().key(key).value(value).endObject().toString();
			return json.substring(1, json.length()-1);//trim off the surrounding { }
		} catch (JSONException ex) {
			System.err.println("Cannot create JSON key value pair from key:"+key+", value:"+value + "\n" +ex.getLocalizedMessage());
			return "";
		}

	}

	private static boolean valid(String s)
	{
		return s != null && !s.trim().isEmpty();
	}

	/*
	 * Returns null if fail, otherwise returns the String response from the post
	 */
	@SuppressWarnings("restriction")
	private static String post(String strUrl, String data) throws Exception{
		URL url = new URL(strUrl);

		final String method = "POST";
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
		if(valid(JSON_RPC_WEBSERVER_USERNAME) && valid(JSON_RPC_WEBSERVER_PASSWORD)){
			String authString = JSON_RPC_WEBSERVER_USERNAME + ":" + JSON_RPC_WEBSERVER_PASSWORD;            
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

		System.out.println("Raw response from POST. Response Code = "+conn.getResponseCode()+" ("+conn.getResponseMessage()+"):\r\n"+ response);
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

	private void closeTCPSocket()
	{
		try
		{
			if(!jsonRPCSocket.isClosed())
			{
				socketTimedOut = true;
				//if(in != null) in.close();
				jsonRPCSocket.close();
				//Config.log(DEBUG, "Socket closed successfully");
			}
		}
		catch(Exception x)
		{
			System.err.println("Error closing socket: " + x.getLocalizedMessage());
		}
	}

	@Override
	public void run()
	{
		waitingForTimeout = true;
		while(true)
		{
			if(jsonRPCSocket != null)
			{                
				if(lastRead != 0)//wait until it's initialized
				{
					long msAgo = (System.currentTimeMillis()-10) - lastRead; //adjust by 10 ms to account for time getting from clock
					if(msAgo > tcpReadTimeout)
					{
						System.out.println("No more data received in the last "+msAgo+" milliseconds, closing JSON-RPC socket.");
						closeTCPSocket();
						break;
					}
				}
				else//not yet initialized, check for init timeout
				{
					long msAgo = ((System.currentTimeMillis()-10) - tcpInit);
					if(msAgo > tcpInitializeTimeout)
					{
						System.out.println("No data was received from XBMC after waiting for "+(msAgo/1000)+" seconds. Cancelling the request.");
						closeTCPSocket();
						break;
					}
				}
			}

			try
			{
				Thread.sleep(50);
			}
			catch(InterruptedException x){
				System.err.println("Sleeping failed (this is unexpected): " +x.getLocalizedMessage());
			}
		}
		lastRead = 0;
		waitingForTimeout = false;
	}


	/**
	 * Public methods for XBMC calls
	 */
	public void updateVideoLibrary()
	{
		try
		{
			JSONObject response = callMethod("VideoLibrary.Scan", 1, null);
			System.out.println(response);
		}
		catch(Exception x)
		{
			System.err.println("Failed to update VideoLibrary using JSON-RPC: "+x.getLocalizedMessage());
		}
	}

	public void cleanVideoLibrary()
	{
		try
		{
			JSONObject response = callMethod("VideoLibrary.Clean", 1, null);
			System.out.println(response);
		}
		catch(Exception x)
		{
			System.err.println("Failed to cleaning VideoLibrary using JSON-RPC: "+x.getLocalizedMessage());
		}
	}
}
