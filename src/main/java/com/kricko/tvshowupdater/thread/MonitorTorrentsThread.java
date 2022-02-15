package com.kricko.tvshowupdater.thread;

import com.kricko.tvshowupdater.configuration.TorrentConfig;
import com.kricko.tvshowupdater.model.Torrent;
import com.kricko.tvshowupdater.utorrent.UTorrent;

import java.io.IOException;
import java.util.List;

public class MonitorTorrentsThread implements Runnable {

	private final TorrentConfig config;

	public MonitorTorrentsThread(TorrentConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		UTorrent uTorrent = new UTorrent(config);
		
		try {
			uTorrent.getToken();
			Torrent torrentList = uTorrent.getListOfTorrents();
			while(torrentList != null && torrentList.getTorrents().size() > 0){
				List<String> hashes = uTorrent.getFinishedHashes(torrentList);
				if(hashes != null && !hashes.isEmpty()){
					uTorrent.removeCompletedTorrents(hashes);
				}
				Thread.sleep(5000);
				torrentList = uTorrent.getListOfTorrents();
			}
			
		} catch (IOException | InterruptedException e) {
			System.err.println("Failed to get the list of active torrents");
		}
	}
}
