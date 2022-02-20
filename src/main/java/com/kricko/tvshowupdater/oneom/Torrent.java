package com.kricko.tvshowupdater.oneom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Torrent {
    private long id;
    private String title;
    @JsonProperty("value")
    private String link;
    @JsonProperty("seed")
    private int seeders;
    @JsonProperty("leech")
    private int leechers;
    private long size;
}
