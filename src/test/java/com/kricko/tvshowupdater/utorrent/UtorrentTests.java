package com.kricko.tvshowupdater.utorrent;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

/**
 */
public class UtorrentTests {

	@Test
	public void getTokenTest(){
		UTorrent uTorrent = new UTorrent();
		try {
			uTorrent.getToken();
		} catch (IOException e) {
			fail();
		}
		
		return;
	}
	
	@Test
	public void getTorrentListTest(){
		UTorrent uTorrent = new UTorrent();
		try {
			uTorrent.getToken();
			uTorrent.getListOfTorrents();
		} catch (IOException e) {
			fail();
		}
		
		return;
	}
}
