/*
 *      Copyright (c) 2004-2014 Matthew Altman & Stuart Boston
 *
 *      This file is part of TheTVDB API.
 *
 *      TheTVDB API is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      any later version.
 *
 *      TheTVDB API is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with TheTVDB API.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.kricko.tvshowupdater.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author matthew.altman
 * @version $Revision: 1.0 $
 */
public class Banners implements Serializable {

    // Default serial UID
    private static final long serialVersionUID = 1L;
    private int seriesId = 0;
    private List<Banner> seriesList = new ArrayList<Banner>();
    private List<Banner> seasonList = new ArrayList<Banner>();
    private List<Banner> posterList = new ArrayList<Banner>();
    private List<Banner> fanartList = new ArrayList<Banner>();

    /**
     * Method getSeriesId.
     * @return int
     */
    public int getSeriesId() {
        return seriesId;
    }

    /**
     * Method getSeriesList.
     * @return List<Banner>
     */
    public List<Banner> getSeriesList() {
        return seriesList;
    }

    /**
     * Method getSeasonList.
     * @return List<Banner>
     */
    public List<Banner> getSeasonList() {
        return seasonList;
    }

    /**
     * Method getPosterList.
     * @return List<Banner>
     */
    public List<Banner> getPosterList() {
        return posterList;
    }

    /**
     * Method getFanartList.
     * @return List<Banner>
     */
    public List<Banner> getFanartList() {
        return fanartList;
    }

    /**
     * Method setSeriesId.
     * @param seriesId int
     */
    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    /**
     * Method setSeriesList.
     * @param seriesList List<Banner>
     */
    public void setSeriesList(List<Banner> seriesList) {
        this.seriesList = seriesList;
    }

    /**
     * Method addSeriesBanner.
     * @param banner Banner
     */
    public void addSeriesBanner(Banner banner) {
        this.seriesList.add(banner);
    }

    /**
     * Method setSeasonList.
     * @param seasonList List<Banner>
     */
    public void setSeasonList(List<Banner> seasonList) {
        this.seasonList = seasonList;
    }

    /**
     * Method addSeasonBanner.
     * @param banner Banner
     */
    public void addSeasonBanner(Banner banner) {
        this.seasonList.add(banner);
    }

    /**
     * Method setPosterList.
     * @param posterList List<Banner>
     */
    public void setPosterList(List<Banner> posterList) {
        this.posterList = posterList;
    }

    /**
     * Method addPosterBanner.
     * @param banner Banner
     */
    public void addPosterBanner(Banner banner) {
        this.posterList.add(banner);
    }

    /**
     * Method setFanartList.
     * @param fanartList List<Banner>
     */
    public void setFanartList(List<Banner> fanartList) {
        this.fanartList = fanartList;
    }

    /**
     * Method addFanartBanner.
     * @param banner Banner
     */
    public void addFanartBanner(Banner banner) {
        this.fanartList.add(banner);
    }

    /**
     * Method addBanner.
     * @param banner Banner
     */
    public void addBanner(Banner banner) {
        if (banner != null) {
            if (seriesId == 0) {
                seriesId = banner.getId();
            }

            if (banner.getBannerType() == BannerListType.SERIES) {
                addSeriesBanner(banner);
            } else if (banner.getBannerType() == BannerListType.SEASON) {
                addSeasonBanner(banner);
            } else if (banner.getBannerType() == BannerListType.POSTER) {
                addPosterBanner(banner);
            } else if (banner.getBannerType() == BannerListType.FANART) {
                addFanartBanner(banner);
            }
        }
    }

    /**
     * Method toString.
     * @return String
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
