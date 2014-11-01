package com.kricko.tvshowupdater;

import java.io.Console;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBException;

import org.apache.commons.httpclient.HttpException;
import org.json.simple.parser.ParseException;

import com.kricko.tvshowupdater.thread.MonitorTorrentsThread;
import com.kricko.tvshowupdater.utils.Config;
import com.kricko.tvshowupdater.xbmc.Xbmc;

/**
 */
public class App 
{
	/**
	 * Method main.
	 * @param args String[]
	 * @throws JAXBException
	 * @throws IOException
	 * @throws ParseException
	 * @throws HttpException
	 */
	public static void main( String[] args ) 
			throws JAXBException, IOException, ParseException, HttpException 
	{
		System.out.println("**********************************");
		System.out.println("Welcome to TV Show Updater");
		System.out.println("");

		if(args.length == 0){
			showInteractiveCommandLine();
		} else {
			doSelectedOption(args[0]);
		}        
	}

	private static void showInteractiveCommandLine(){
		Console console = System.console();

		if (console == null)   
		{  
			System.err.println("No console.");  
			System.exit(1);  
		}

		while (true)  
		{
			System.out.println("Please enter one of the following options:");
			System.out.println("\t1 - To check if there are shows you need to download");
			System.out.println("\t2 - To tidy up existing folders");
			System.out.println("\t3 - Update XBMC host video library");
			System.out.println("\t4 - Clean XBMC servers video library");
			System.out.println("\t0 - EXIT");
			System.out.println("");

			try {
				String option = console.readLine("What is your choice: ");
				doSelectedOption(option);
			} catch (JAXBException | IOException | ParseException | HttpException e) {
				System.out.println("Unexpected error");
				System.exit(-1);
			}
		}
	}

	/**
	 * Method doSelectedOption.
	 * @param option String
	 * @throws JAXBException
	 * @throws IOException
	 * @throws ParseException
	 * @throws HttpException
	 */
	private static void doSelectedOption(String option) 
			throws JAXBException, IOException, ParseException, HttpException{
		if(option != null){
			if(option.equals("update") || option.equals("1")){
				if(Config.getInstance().updateBeforeDownload()){
					RefactorFiles.tidyFolders(false);
				}
				boolean newDownloads = DownloadShows.doDownload();
				
				if(newDownloads){
					doMonitorTorrents();
					RefactorFiles.tidyFolders(true);
					String[] hosts = Config.getInstance().getProperty("xbmc.host_list").split(",");
					Xbmc.updateHostVideos(hosts);
				}
			} else if(option.equals("tidyup") || option.equals("2")){
				RefactorFiles.tidyFolders(true);
			} else if(option.equals("xbmcupdate") || option.equals("3")){
				String[] hosts = Config.getInstance().getProperty("xbmc.host_list").split(",");
				Xbmc.updateHostVideos(hosts);
			} else if(option.equals("xbmcclean") || option.equals("4")){
				String[] hosts = Config.getInstance().getProperty("xbmc.host_list").split(",");
				Xbmc.cleanVideoLibrary(hosts);
			} else if(option.equals("0")){
				System.exit(0);
			} else {
				System.out.println("Invalid option, try again");
				showInteractiveCommandLine();
			}
		}
	}
	
	private static void doMonitorTorrents() {
		ExecutorService thread = Executors.newSingleThreadExecutor();
		thread.execute(new MonitorTorrentsThread());
		
		thread.shutdown();

		try {
			thread.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			return;
		}
	}
}
