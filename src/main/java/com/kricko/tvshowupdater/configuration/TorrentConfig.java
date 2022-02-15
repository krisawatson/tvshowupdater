package com.kricko.tvshowupdater.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TorrentConfig {
    private String client;

    private String webhost;

    private int webport;

    private String webuser;

    private String webpass;
}
