package com.kricko.tvshowupdater;

import java.util.List;

import com.kricko.tvshowupdater.refactor.FolderRefactorer;
import com.kricko.tvshowupdater.utils.TvShowUtils;

public class RefactorFolders {

	public static void main(String[] args) {

		List<String> directories = TvShowUtils.getListOfTidyUpDirs();
		FolderRefactorer refactorer = new FolderRefactorer();

		for(String dir:directories){
			refactorer.doRefactor(dir);
		}
	}

}
