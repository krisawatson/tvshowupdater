package com.kricko.tvshowupdater.model;

import java.util.List;

public class Torrent {

	private int build;
	
	private List<List<Object>> torrents;
	
	private List<String> label;
	
	private String torrentc;
	
	private List<String> rssfeeds;
	
	private List<String> rssfilters;

	public int getBuild() {
		return build;
	}

	public void setBuild(int build) {
		this.build = build;
	}

	public List<List<Object>> getTorrents() {
		return torrents;
	}

	public void setTorrents(List<List<Object>> torrents) {
		this.torrents = torrents;
	}

	public List<String> getLabel() {
		return label;
	}

	public void setLabel(List<String> label) {
		this.label = label;
	}

	public String getTorrentc() {
		return torrentc;
	}

	public void setTorrentc(String torrentc) {
		this.torrentc = torrentc;
	}

	public List<String> getRssfeeds() {
		return rssfeeds;
	}

	public void setRssfeeds(List<String> rssfeeds) {
		this.rssfeeds = rssfeeds;
	}

	public List<String> getRssfilters() {
		return rssfilters;
	}

	public void setRssfilters(List<String> rssfilters) {
		this.rssfilters = rssfilters;
	}
}
