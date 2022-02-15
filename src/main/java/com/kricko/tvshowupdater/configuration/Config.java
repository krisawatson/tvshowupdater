package com.kricko.tvshowupdater.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Config {
    @JsonProperty("torrent")
    private TorrentConfig torrentConfig;

    @JsonProperty("updateBeforeDownload")
    private boolean updateBeforeDownload;

    @JsonProperty("showRegex")
    private String showRegex;
}
