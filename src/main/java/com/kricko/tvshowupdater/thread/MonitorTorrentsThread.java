package com.kricko.tvshowupdater.thread;

import com.kricko.tvshowupdater.configuration.TorrentConfig;
import com.kricko.tvshowupdater.model.Torrent;
import com.kricko.tvshowupdater.torrent.Filter;
import com.kricko.tvshowupdater.torrent.QBitTorrent;

import java.io.IOException;
import java.util.List;

public class MonitorTorrentsThread implements Runnable {

	private final TorrentConfig config;

	public MonitorTorrentsThread(TorrentConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		QBitTorrent torrentClient = new QBitTorrent(config);
		
		try {
			List<Torrent> torrentList = torrentClient.getListOfTorrents(Filter.ALL);
			while(!torrentList.isEmpty()){
				List<Torrent> completedTorrents = torrentClient.getListOfTorrents(Filter.COMPLETED);
				completedTorrents.forEach(torrent -> torrentClient.removeCompletedTorrents(torrent.getHash()));
				Thread.sleep(5000);
				torrentList = torrentClient.getListOfTorrents(Filter.ALL);
			}
			
		} catch (IOException | InterruptedException e) {
			System.err.println("Failed to get the list of active torrents");
		}
	}
}
