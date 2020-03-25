package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.thread.MonitorTorrentsThread;
import com.kricko.tvshowupdater.utils.Config;
import com.kricko.tvshowupdater.xbmc.Xbmc;
import org.apache.commons.httpclient.HttpException;
import org.json.simple.parser.ParseException;

import javax.xml.bind.JAXBException;
import java.io.Console;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
			throws JAXBException, IOException, ParseException, HttpException, URISyntaxException, InterruptedException {
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
			System.out.println("\t5 - Execute trakt script");
			System.out.println("\t0 - EXIT");
			System.out.println("");

			try {
				String option = console.readLine("What is your choice: ");
				doSelectedOption(option);
			} catch (IOException | ParseException | HttpException | InterruptedException e) {
				System.out.println("Unexpected error");
				System.exit(-1);
			}
		}
	}

	/**
	 * Method doSelectedOption.
	 * @param option String
	 * @throws IOException
	 * @throws ParseException
	 * @throws HttpException
	 * @throws InterruptedException
	 */
	private static void doSelectedOption(String option)
			throws IOException, ParseException, HttpException, InterruptedException {
		if(option != null){
			if(option.equals("update") || option.equals("1")){
				Config config = Config.getInstance();
				if(config.updateBeforeDownload()){
					RefactorFiles.tidyFolders(true);
				}
				boolean newDownloads = DownloadShows.doDownload();
				
				if(newDownloads){
					doMonitorTorrents();
					RefactorFiles.tidyFolders(false);
					if (config.isXbmcUpdate()) {
						String[] hosts = Config.getInstance().getProperty("xbmc.host_list").split(",");
						Xbmc.updateHostVideos(hosts);
						Xbmc.executeTraktAddon(hosts);
					}
				}
			} else if(option.equals("tidyup") || option.equals("2")){
				RefactorFiles.tidyFolders(true);
			} else if(option.equals("xbmcupdate") || option.equals("3")){
				String[] hosts = Config.getInstance().getProperty("xbmc.host_list").split(",");
				Xbmc.updateHostVideos(hosts);
			} else if(option.equals("xbmcclean") || option.equals("4")){
				String[] hosts = Config.getInstance().getProperty("xbmc.host_list").split(",");
				Xbmc.cleanVideoLibrary(hosts);
			} else if(option.equals("xbmctrakt") || option.equals("5")){
				String[] hosts = Config.getInstance().getProperty("xbmc.host_list").split(",");
				Xbmc.executeTraktAddon(hosts);
			} else if(option.equals("0")){
				System.exit(0);
			} else {
				System.out.println("Invalid option, try again");
				showInteractiveCommandLine();
			}
		}
	}
	
	private static void doMonitorTorrents() throws InterruptedException {
		ExecutorService thread = Executors.newSingleThreadExecutor();
		thread.execute(new MonitorTorrentsThread());
		
		thread.shutdown();

		thread.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
	}
}
