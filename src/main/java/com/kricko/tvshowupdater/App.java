package com.kricko.tvshowupdater;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kricko.tvshowupdater.configuration.Config;
import com.kricko.tvshowupdater.model.Shows;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import static com.kricko.tvshowupdater.DownloadShows.doDownload;
import static com.kricko.tvshowupdater.RefactorFiles.tidyFolders;
import static com.kricko.tvshowupdater.parser.TvShowParser.parseShows;
import static com.kricko.tvshowupdater.thread.MonitorTorrents.waitForTorrentsToComplete;
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
		CommandLineParser parser = DefaultParser.builder().build();
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
		try (InputStream inputStream = requireNonNull(configFile).openStream()) {
			config = mapper.readValue(inputStream, Config.class);
		}
	}

	/**
	 * Method doSelectedOption.
	 * @param option String
	 */
	private static void doSelectedOption(String option, String showsFilePath) throws IOException, URISyntaxException {
		boolean tidyExisting = config.isTidyExisting();
		File showsFile = (null == showsFilePath)
						 ? new File(requireNonNull(App.class.getClassLoader().getResource("tvshows.json")).toURI())
						 : new File(showsFilePath);
		Shows shows = parseShows(showsFile);
		if (option != null){
			switch (option) {
				case "update", "1" -> updateShows(tidyExisting, shows);
				case "tidyup", "2" -> tidyFolders(tidyExisting, shows);
				case "missing", "3" -> IdentifyMissing.identifyMissing(shows);
				case "0" -> System.exit(0);
				default -> log.warn("Invalid option: {}", option);
			}
		}
	}

	private static void updateShows(boolean tidyExisting, Shows shows) {
		// New approach
		doDownload(config, shows);
		waitForTorrentsToComplete(config.getQBitTorrentConfig()).whenComplete((ignore, error) -> {
			if (null != error) {
				log.error("Failed while monitoring torrents, {}", error.getLocalizedMessage());
			} else {
				tidyFolders(tidyExisting, shows);
			}
		});
	}
}
