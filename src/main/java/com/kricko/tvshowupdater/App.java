package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.thread.MonitorTorrentsThread;
import com.kricko.tvshowupdater.utils.Config;
import org.apache.commons.httpclient.HttpException;
import org.json.simple.parser.ParseException;

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
	 */
	public static void main( String[] args )
			throws IOException, ParseException, HttpException, InterruptedException, URISyntaxException {
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
			System.out.println("\t1 - Download latest episodes");
			System.out.println("\t2 - To tidy up existing folders");
			System.out.println("\t3 - Identify missing episodes");
			System.out.println("\t0 - EXIT");
			System.out.println("");

			try {
				String option = console.readLine("What is your choice: ");
				doSelectedOption(option);
			} catch (IOException | ParseException | HttpException | InterruptedException | URISyntaxException e) {
				System.out.println("Unexpected error");
				System.exit(-1);
			}
		}
	}

	/**
	 * Method doSelectedOption.
	 * @param option String
	 */
	private static void doSelectedOption(String option)
			throws IOException, ParseException, HttpException, InterruptedException, URISyntaxException {
		if (option != null){
			switch (option) {
				case "update":
				case "1":
					Config config = Config.getInstance();
					if (config.updateBeforeDownload()) {
						RefactorFiles.tidyFolders(true);
					}

					if (DownloadShows.doDownload()) {
						doMonitorTorrents();
						RefactorFiles.tidyFolders(false);
					}
					break;
				case "tidyup":
				case "2":
					RefactorFiles.tidyFolders(true);
					break;
				case "missing":
				case "3":
					IdentifyMissingEpisodes.identifyMissing();
					break;
				case "0":
					System.exit(0);
				default:
					System.out.println("Invalid option, try again");
					showInteractiveCommandLine();
					break;
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
