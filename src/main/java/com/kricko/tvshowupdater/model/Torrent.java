package com.kricko.tvshowupdater.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Torrent {
	@JsonProperty("content_path")
	private String contentPath;
	@JsonProperty("dlspeed")
	private String downloadSpeed;
	private String hash;
	@JsonProperty("magnet_uri")
	private String magnetUri;
}
