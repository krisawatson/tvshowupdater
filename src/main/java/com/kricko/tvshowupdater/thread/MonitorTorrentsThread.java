package com.kricko.tvshowupdater.thread;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.kricko.tvshowupdater.model.Torrent;
import com.kricko.tvshowupdater.utorrent.UTorrent;

public class MonitorTorrentsThread implements Runnable {

	@Override
	public void run() {
		UTorrent uTorrent = new UTorrent();
		
		try {
			uTorrent.getToken();
			Torrent torrentList = uTorrent.getListOfTorrents();
			while(torrentList.getTorrents().size() > 0){
				List<String> hashes = uTorrent.getFinishedHashes(torrentList);
				if(hashes != null && !hashes.isEmpty()){
					uTorrent.removeCompletedTorrents(hashes);
				}
				torrentList = uTorrent.getListOfTorrents();
			}
			
		} catch (IOException | ParseException e) {
			return;
		}
	}
}
