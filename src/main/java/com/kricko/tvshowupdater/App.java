package com.kricko.tvshowupdater;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kricko.tvshowupdater.configuration.Config;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.thread.MonitorTorrentsThread;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.kricko.tvshowupdater.DownloadShows.doDownload;
import static com.kricko.tvshowupdater.parser.TvShowParser.parseShows;
import static java.util.Objects.requireNonNull;

/**
 */
@Slf4j
public class App {

	private static Config config;
	/**
	 * Method main.
	 * @param args String[]
	 */
	public static void main( String[] args )
			throws IOException, InterruptedException, URISyntaxException, org.json.simple.parser.ParseException {

		Options options = new Options();

		Option option = new Option("o", "option", true, "arg: update, tidyup, missing");
		option.setRequired(true);
		options.addOption(option);

		Option showsJsonOpt = new Option("s", "shows", true, "tv shows json file");
		showsJsonOpt.setRequired(true);
		options.addOption(showsJsonOpt);

		Option configOpt= new Option("c", "config", true, "config file path");
		configOpt.setRequired(false);
		options.addOption(configOpt);

		Option useOneOmOpt= new Option("oo", "oneom", false, "Use One Om for downloads");
		configOpt.setRequired(false);
		options.addOption(useOneOmOpt);

		CommandLineParser parser = new BasicParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd = null;//not a good practice, it serves it purpose

		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			log.error("Failed to parse command options", e);
			formatter.printHelp("java -jar tvshowupdater.jar ", options);

			System.exit(1);
		}

		String selectedOption = cmd.getOptionValue("option");
		String showsFilePath = cmd.getOptionValue("shows");
		String configFilePath = cmd.getOptionValue("config");
		log.info("**********************************");
		log.info("Welcome to TV Show Updater");
		log.info("**********************************");

		ObjectMapper mapper = new ObjectMapper();
		URL configFile = (null == configFilePath)
						  ? App.class.getClassLoader().getResource("config.json")
						  : new File(configFilePath).toURI().toURL();
		config = mapper.readValue(configFile, Config.class);

		doSelectedOption(selectedOption, showsFilePath);
	}

	/**
	 * Method doSelectedOption.
	 * @param option String
	 */
	private static void doSelectedOption(String option, String showsFilePath)
			throws IOException, InterruptedException, URISyntaxException, org.json.simple.parser.ParseException {
		boolean tidyExisting = config.isTidyExisting();
		File showsFile = (null == showsFilePath)
						 ? new File(requireNonNull(App.class.getClassLoader().getResource("tvshows.json")).toURI())
						 : new File(showsFilePath);
		Shows shows = parseShows(showsFile);
		if (option != null){
			switch (option) {
				case "update":
				case "1":
					updateShows(tidyExisting, shows);
					break;
				case "tidyup":
				case "2":
					RefactorFiles.tidyFolders(tidyExisting, shows);
					break;
				case "missing":
				case "3":
					IdentifyMissing.identifyMissing(shows);
					break;
				case "0":
					System.exit(0);
			}
		}
	}
	
	private static void doMonitorTorrents() throws InterruptedException {
		ExecutorService thread = Executors.newSingleThreadExecutor();
		thread.execute(new MonitorTorrentsThread(config.getQBitTorrentConfig()));
		
		thread.shutdown();

		thread.awaitTermination(60L, TimeUnit.SECONDS);
	}

	private static void updateShows(boolean tidyExisting, Shows shows) throws InterruptedException {
		if (tidyExisting) {
			RefactorFiles.tidyFolders(true, shows);
		}
		// New approach
		if (doDownload(config, shows)) {
			doMonitorTorrents();
			RefactorFiles.tidyFolders(false, shows);
		}
	}
}
