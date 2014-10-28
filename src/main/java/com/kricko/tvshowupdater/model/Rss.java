package com.kricko.tvshowupdater.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 */
@XmlRootElement(name="rss", namespace="")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rss {
	
	@XmlElement(name="channel", namespace="")
	private Channel channel;

	/**
	 * Method getChannel.
	 * @return Channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * Method setChannel.
	 * @param channel Channel
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
