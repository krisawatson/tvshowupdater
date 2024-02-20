package com.kricko.tvshowupdater;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kricko.tvshowupdater.configuration.Config;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.thread.MonitorTorrentsThread;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class App {

	private static final Logger log = LoggerFactory.getLogger(App.class);
	private static Config config;
	/**
	 * Method main.
	 * @param args String[]
	 */
	public static void main(String[] args) {
		try {
			Options options = createOptions();
			CommandLine cmd = parseCommandLine(options, args);

			String selectedOption = cmd.getOptionValue("option");
			String showsFilePath = cmd.getOptionValue("shows");
			String configFilePath = cmd.getOptionValue("config");

			log.info("**********************************");
			log.info("Welcome to TV Show Updater");
			log.info("**********************************");

			loadConfig(configFilePath);
			doSelectedOption(selectedOption, showsFilePath);
		} catch (Exception e) {
			log.error("An error occurred", e);
		}
	}

	private static Options createOptions() {
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

		return options;
	}

	private static CommandLine parseCommandLine(Options options, String[] args) throws ParseException {
		CommandLineParser parser = new BasicParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd = parser.parse(options, args);

		if (!cmd.hasOption("option") || !cmd.hasOption("shows")) {
			log.error("Required options are missing");
			formatter.printHelp("java -jar tvshowupdater.jar ", options);
			System.exit(1);
		}

		return cmd;
	}

	private static void loadConfig(String configFilePath) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		URL configFile = (configFilePath == null)
				? App.class.getClassLoader().getResource("config.json")
				: new File(configFilePath).toURI().toURL();
		config = mapper.readValue(configFile, Config.class);
	}

	/**
	 * Method doSelectedOption.
	 * @param option String
	 */
	private static void doSelectedOption(String option, String showsFilePath)
			throws IOException, InterruptedException, URISyntaxException {
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
					break;
				default:
					log.warn("Invalid option: {}", option);
			}
		}
	}
	
	private static void doMonitorTorrents() throws InterruptedException {
		ExecutorService thread = Executors.newSingleThreadExecutor();
		try {
			thread.execute(new MonitorTorrentsThread(config.getQBitTorrentConfig()));
		} finally {
			thread.shutdown();
			thread.awaitTermination(60L, TimeUnit.SECONDS);
		}
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
