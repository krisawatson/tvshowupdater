package com.kricko.tvshowupdater.torrent;

public enum Filter {
    ALL("all"),
    COMPLETED("completed"),
    DOWNLOADING("downloading");

    private String filterName;

    Filter(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterName() {
        return filterName;
    }
}
