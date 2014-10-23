package com.kricko.tvshowupdater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBException;

import org.json.simple.parser.ParseException;

public class App 
{
	public static void main( String[] args ) throws JAXBException, IOException, ParseException
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
		System.out.println("Please enter one of the following options:");
    	System.out.println("\t1 - To check if there are shows you need to download");
    	System.out.println("\t2 - To tidy up existing folders");
    	System.out.println("");
    	System.out.println("What is your choice: ");
    	
    	// Open up standard input for reading
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String option = null;
        
        try {
            option = br.readLine();
            br.close();
         } catch (IOException ioe) {
            System.out.println("IO error trying to read your option!");
            System.exit(1);
         }
        
        try {
			doSelectedOption(option);
		} catch (JAXBException | IOException | ParseException e) {
			System.out.println("Unexpected error");
            System.exit(1);
		}
	}
	
	private static void doSelectedOption(String option) throws JAXBException, IOException, ParseException{
		if(option != null){
			if(option.equals("update") || option.equals("1")){
				DownloadShows.doDownload();
			} else if(option.equals("tidyup") || option.equals("2")){
				RefactorFolders.tidyFolders();
			} else {
				System.out.println("Invalid option, try again");
				showInteractiveCommandLine();
			}
		}
	}
}
