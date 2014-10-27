package com.kricko.tvshowupdater;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.refactor.FileRefactorer;
import com.kricko.tvshowupdater.utils.TvShowUtils;

public class RefactorFiles {

	public static void tidyFolders(boolean existing) {

		FileRefactorer refactorer = new FileRefactorer();

		Shows shows;
		try {
			List<String> directories = TvShowUtils.getListOfTidyUpDirs();
			for(String dir:directories){
				refactorer.doRefactor(null, dir);
			}

			if(existing){
				shows = TvShowUtils.getListOfShows();
				if(shows != null){
					List<Details> details = shows.getShows();
					for(Details detail:details){
						refactorer.doRefactor(detail.getName(), detail.getPath());
					}
				}
			}
		} catch (IOException | ParseException e) {
			System.err.println("Failed to refactor files " + e.getLocalizedMessage());
		}
	}

}
