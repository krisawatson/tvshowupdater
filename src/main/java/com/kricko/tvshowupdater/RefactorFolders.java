package com.kricko.tvshowupdater;

import java.util.List;

import com.kricko.tvshowupdater.refactor.FolderRefactorer;
import com.kricko.tvshowupdater.utils.TvShowUtils;

public class RefactorFolders {

	public static void tidyFolders() {

		List<String> directories = TvShowUtils.getListOfTidyUpDirs();
		FolderRefactorer refactorer = new FolderRefactorer();

		for(String dir:directories){
			refactorer.doRefactor(dir);
		}
	}

}
