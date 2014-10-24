package com.kricko.tvshowupdater;

import java.util.List;

import com.kricko.tvshowupdater.refactor.FileRefactorer;
import com.kricko.tvshowupdater.utils.TvShowUtils;

public class RefactorFiles {

	public static void tidyFolders() {

		List<String> directories = TvShowUtils.getListOfTidyUpDirs();
		FileRefactorer refactorer = new FileRefactorer();

		for(String dir:directories){
			refactorer.doRefactor(dir);
		}
	}

}
