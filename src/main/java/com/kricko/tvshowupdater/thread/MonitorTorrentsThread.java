package com.kricko.tvshowupdater.thread;

import com.kricko.tvshowupdater.configuration.TorrentConfig;
import com.kricko.tvshowupdater.model.Torrent;
import com.kricko.tvshowupdater.torrent.Filter;
import com.kricko.tvshowupdater.torrent.QBitTorrent;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MonitorTorrentsThread implements Runnable {

	private final TorrentConfig config;

	public MonitorTorrentsThread(TorrentConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		QBitTorrent torrentClient = new QBitTorrent(config);
		
		try {
			while(!torrentClient.getListOfTorrents(Filter.ALL).isEmpty()){
				List<Torrent> completedTorrents = torrentClient.getListOfTorrents(Filter.COMPLETED);
				String hashes = completedTorrents.stream().map(Torrent::getHash).collect(Collectors.joining("|"));
				if (!hashes.isEmpty())
					torrentClient.removeCompletedTorrents(hashes);
			}
			
		} catch (IOException e) {
			System.err.println("Failed to get the list of active torrents");
		}
	}
}
