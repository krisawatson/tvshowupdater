package com.kricko.tvshowupdater.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Details {
    private String name;
    private Optional<Integer> rssFeedId;
    private String regexName;
    private String path;
    private List<Integer> skipSeason;
    private Optional<String> tvdbSeriesId;
    private List<String> ignoreMissing;
    private List<String> skipTorrentsWith;

    public static class DetailsBuilder {
        private Optional<Integer> rssFeedId = Optional.empty();
        private List<Integer> skipSeason = new ArrayList<>();
        private List<String> ignoreMissing = new ArrayList<>();
        private List<String> skipTorrentsWith = new ArrayList<>();
        private Optional<String> tvdbSeriesId = Optional.empty();
    }
}
