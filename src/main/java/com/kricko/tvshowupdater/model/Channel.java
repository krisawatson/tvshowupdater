package com.kricko.tvshowupdater.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Channel {

	@XmlElement(name="title")
	private String title;
	
	@XmlElement(name="link")
	private String link;
	
	@XmlElement(name="ttl")
	private String ttl;
	
	@XmlElement(name="description")
	private String description;
	
	@XmlElement(name="item")
	private List<Item> item;

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
	 * Method getTtl.
	 * @return String
	 */
	public String getTtl() {
		return ttl;
	}

	/**
	 * Method setTtl.
	 * @param ttl String
	 */
	public void setTtl(String ttl) {
		this.ttl = ttl;
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
	 * Method getItem.
	 * @return List<Item>
	 */
	public List<Item> getItem() {
		return item;
	}

	/**
	 * Method setItem.
	 * @param item List<Item>
	 */
	public void setItem(List<Item> item) {
		this.item = item;
	} 
}
