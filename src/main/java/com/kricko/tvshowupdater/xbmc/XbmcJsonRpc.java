package com.kricko.tvshowupdater.xbmc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.kricko.tvshowupdater.utils.HttpUtils;
import com.kricko.tvshowupdater.utils.TvShowUtils;

/**
 */
public class XbmcJsonRpc implements Runnable
{
	private boolean useHTTP=false, useRawTCP=true;
	private int port = 9090, maxRetries = 1;
	private String server = "localhost";
	private long tcpReadTimeout = 50, tcpInitializeTimeout = 1000 * 60;//give XBMC x seconds to start returning data, then cancel the call
	private long lastRead = 0, tcpInit = 0;
	private boolean socketTimedOut = false, waitingForTimeout = false;;
	private Socket jsonRPCSocket = null;

	private String webserverUsername = "xbmc";
	private String webserverPassword = null;

	/**
	 * Constructor for XbmcJsonRpc.
	 * @param server String
	 * @param port int
	 * @param useHttp boolean
	 * @param maxRetries int
	 */
	public XbmcJsonRpc(String server, int port, boolean useHttp, int maxRetries,
			String username, String password){
		this.server = server;
		this.port = port;
		this.useHTTP = useHttp;
		this.useRawTCP = !useHttp;
		this.maxRetries = maxRetries;
		this.webserverUsername = username;
		this.webserverPassword = password;
	}

	/**
	 * Method callMethod.
	 * @param method String
	 * @param id int
	 * @param params Map<String,Object>
	 * @return JSONObject
	 */
	public JSONObject callMethod(String method, int id, Map<String,Object> params)
	{
		for(int i=0;i<maxRetries;i++)
		{
			tcpReadTimeout = tcpReadTimeout * (i+1);
			
			JSONObject json = callMethodWithRetry(method, id, params);
			if(json != null)
			{
				if(i > 0)
					System.out.println("JSON was successfully retrieved after attempt #"+(i+1));
				return json;
			}
			else
			{
				System.out.println("JSON Returned was not valid (attempt " + (i + 1) + " of "+maxRetries+")" 
						+ (i == (maxRetries - 1) ? "Ending retries, JSON is not valid" : ", will try again... "));
				if(i < 2)//if will try again, sleep a little bit
				{
					try{Thread.sleep((i+1) * 2500);}catch(Exception x){}
				}
			}
		}        
		return null;//reached end of tired and no valid json was returned
	}

	/**
	 * Method callMethodWithRetry.
	 * @param method String
	 * @param id int
	 * @param params Map<String,Object>
	 * @return JSONObject
	 */
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
		if(id != -1){
			cmd += ", \"id\": \""+id+"\"";
		}
		cmd += "}";

		System.out.println("Connecting to "+server+"(JSON-RPC) and sending command: " + cmd);

		StringBuilder response = new StringBuilder();
		if(useHTTP)
		{
			//HTTP POST
			//CURL example: curl.exe -i -X POST -d "{\"jsonrpc\": \"2.0\", \"method\": \"JSONRPC.Version\", \"id\": 1}" http://localhost:8080/jsonrpc                        

			String rpcprefix = server+":"+port+"/jsonrpc";
			if(!rpcprefix.toLowerCase().startsWith("http"))
				rpcprefix = "http://"+rpcprefix;            
			try{
				String authString = null;
				if(TvShowUtils.valid(webserverUsername) && TvShowUtils.valid(webserverPassword)){
					authString = webserverUsername + ":" + webserverPassword;
				}
				String strResponse = HttpUtils.post(rpcprefix, cmd, authString);
				if(!TvShowUtils.valid(strResponse)) throw new Exception("No reponse or invalid response code.");
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

	/**
	 * Method jsonKeyValue.
	 * @param key String
	 * @param value Object
	 * @return String
	 */
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

	/**
	 * Method run.
	 * @see java.lang.Runnable#run()
	 */
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
	
	public void getAddOns(Map<String,Object> params)
	{
		try
		{
			JSONObject response = callMethod("Addons.GetAddons", 1, params);
			System.out.println(response);
		}
		catch(Exception x)
		{
			System.err.println("Failed to cleaning VideoLibrary using JSON-RPC: "+x.getLocalizedMessage());
		}
	}
	
	public void executeAddon(Map<String,Object> params){
		try
		{
			JSONObject response = callMethod("Addons.ExecuteAddon", -1, params);
			System.out.println(response);
		}
		catch(Exception x)
		{
			System.err.println("Failed to cleaning VideoLibrary using JSON-RPC: "+x.getLocalizedMessage());
		}
	}
}
