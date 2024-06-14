package com.kricko.tvshowupdater.thread;

import com.kricko.tvshowupdater.configuration.TorrentConfig;
import com.kricko.tvshowupdater.model.Torrent;
import com.kricko.tvshowupdater.torrent.Filter;
import com.kricko.tvshowupdater.torrent.QBitTorrent;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Slf4j
public class MonitorTorrents {

	public static CompletionStage<Void> waitForTorrentsToComplete(TorrentConfig config) {
		QBitTorrent torrentClient = new QBitTorrent(config);
		
		try {
			var emptyListOfTorrents = torrentClient.getListOfTorrents(Filter.ALL).isEmpty();
			while(!emptyListOfTorrents){
				List<Torrent> completedTorrents = torrentClient.getListOfTorrents(Filter.COMPLETED);
				String hashes = completedTorrents.parallelStream().map(Torrent::getHash).collect(Collectors.joining("|"));
				if (!hashes.isEmpty())
					torrentClient.removeCompletedTorrents(hashes);
				Thread.sleep(1000);
				emptyListOfTorrents = torrentClient.getListOfTorrents(Filter.ALL).isEmpty();
			}
		} catch (IOException | InterruptedException e) {
			log.error("Failed to get the list of active torrents", e);
			return CompletableFuture.completedStage(null);
		}
		return CompletableFuture.completedStage(null);
	}
}
