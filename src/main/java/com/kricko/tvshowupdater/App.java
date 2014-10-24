package com.kricko.tvshowupdater;

import java.io.Console;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.commons.httpclient.HttpException;
import org.json.simple.parser.ParseException;

import com.kricko.tvshowupdater.utils.TvShowProperties;
import com.kricko.tvshowupdater.xbmc.Xbmc;

public class App 
{
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

	private static void doSelectedOption(String option) 
			throws JAXBException, IOException, ParseException, HttpException{
		if(option != null){
			if(option.equals("update") || option.equals("1")){
				if(TvShowProperties.getInstance().updateBeforeDownload()){
					RefactorFiles.tidyFolders();
				}
				DownloadShows.doDownload();
			} else if(option.equals("tidyup") || option.equals("2")){
				RefactorFiles.tidyFolders();
			} else if(option.equals("xbmcupdate") || option.equals("3")){
				String[] hosts = TvShowProperties.getInstance().getProperty("xbmc.host_list").split(",");
				Xbmc.updateHostVideos(hosts);
			} else if(option.equals("xbmcclean") || option.equals("4")){
				String[] hosts = TvShowProperties.getInstance().getProperty("xbmc.host_list").split(",");
				Xbmc.cleanVideoLibrary(hosts);
			} else if(option.equals("0")){
				System.exit(0);
			} else {
				System.out.println("Invalid option, try again");
				showInteractiveCommandLine();
			}
		}
	}
}
