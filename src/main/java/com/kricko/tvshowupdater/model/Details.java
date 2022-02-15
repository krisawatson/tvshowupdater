package com.kricko.tvshowupdater.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class Details {
	private String name;
	private String rssfeed;
	private String regexName;
	private String path;
	private List<Integer> skipSeason;
	private Optional<String> tvdbSeriesId;
	private List<String> ignoreMissing;
}
