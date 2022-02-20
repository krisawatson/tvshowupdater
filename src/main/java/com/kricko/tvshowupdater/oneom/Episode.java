package com.kricko.tvshowupdater.oneom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Episode {
    private long id;
    @JsonProperty("serial_id")
    private long serialId;
    private String season;
    @JsonProperty("ep")
    private String episode;
    private List<Torrent> torrent;
}
