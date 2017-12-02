package com.kricko.tvshowupdater.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 */
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
	
	@XmlElement(name="show_id")
	private int showId;
	
	@XmlElement(name="show_name")
	private String showName;
	
	@XmlElement(name="episode_id")
	private Integer episode;
	
	@XmlElement(name="info_hash")
	private String infoHash;
	
	@XmlElement(name="raw_title")
	private String rawTitle;

	@XmlElement(name="enclosure")
	private String enclosure;
	
	/**
	 * Method getTitle.
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Method setTitle.
	 * @param title String
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Method getLink.
	 * @return String
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Method setLink.
	 * @param link String
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Method getGuid.
	 * @return String
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * Method setGuid.
	 * @param guid String
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * Method getPubDate.
	 * @return String
	 */
	public String getPubDate() {
		return pubDate;
	}

	/**
	 * Method setPubDate.
	 * @param pubDate String
	 */
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * Method getDescription.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Method setDescription.
	 * @param description String
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Method getShowId.
	 * @return int
	 */
	public int getShowId() {
		return showId;
	}

	/**
	 * Method setShowId.
	 * @param showId int
	 */
	public void setShowId(int showId) {
		this.showId = showId;
	}

	/**
	 * Method getShowName.
	 * @return String
	 */
	public String getShowName() {
		return showName;
	}

	/**
	 * Method setShowName.
	 * @param showName String
	 */
	public void setShowName(String showName) {
		this.showName = showName;
	}

	/**
	 * Method getEpisode.
	 * @return int
	 */
	public int getEpisode() {
		return episode;
	}

	/**
	 * Method setEpisode.
	 * @param episode int
	 */
	public void setEpisode(int episode) {
		this.episode = episode;
	}

	/**
	 * Method getInfoHash.
	 * @return String
	 */
	public String getInfoHash() {
		return infoHash;
	}

	/**
	 * Method setInfoHash.
	 * @param infoHash String
	 */
	public void setInfoHash(String infoHash) {
		this.infoHash = infoHash;
	}

	/**
	 * Method getRawTitle.
	 * @return String
	 */
	public String getRawTitle() {
		return rawTitle;
	}

	/**
	 * Method setRawTitle.
	 * @param rawTitle String
	 */
	public void setRawTitle(String rawTitle) {
		this.rawTitle = rawTitle;
	}

	/**
	 * Method getEnclosure.
	 * @return String
	 */
	public String getEnclosure() {
		return enclosure;
	}

	/**
	 * Method setEnclosure.
	 * @param enclosure String
	 */
	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	/**
	 * Method compareTo.
	 * @param item Item
	 * @return int
	 */
	public int compareTo(Item item) {
		return this.episode.compareTo(item.episode);
	}
}
