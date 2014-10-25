package com.kricko.tvshowupdater.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item implements Comparable<Item>{
	
	@XmlElement(name="title")
	private String title;
	
	@XmlElement(name="link")
	private String link;
	
	@XmlElement(name="guid")
	private String guid;
	
	@XmlElement(name="pubDate")
	private String pubDate;
	
	@XmlElement(name="description")
	private String description;
	
	@XmlElement(name="showid", namespace="showrss")
	private int showId;
	
	@XmlElement(name="showname", namespace="showrss")
	private String showName;
	
	@XmlElement(name="episode", namespace="showrss")
	private Integer episode;
	
	@XmlElement(name="info_hash", namespace="showrss")
	private String infoHash;
	
	@XmlElement(name="rawtitle", namespace="showrss")
	private String rawTitle;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public String getInfoHash() {
		return infoHash;
	}

	public void setInfoHash(String infoHash) {
		this.infoHash = infoHash;
	}

	public String getRawTitle() {
		return rawTitle;
	}

	public void setRawTitle(String rawTitle) {
		this.rawTitle = rawTitle;
	}

	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	private String enclosure;

	public int compareTo(Item item) {
		return this.episode.compareTo(item.episode);
	}
}
