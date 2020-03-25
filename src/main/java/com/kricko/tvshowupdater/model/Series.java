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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author altman.matthew
 * @version $Revision: 1.0 $
 */
public class Series implements Serializable {

    // Default serial UID
    private static final long serialVersionUID = 1L;
    private String id;
    private String seriesId;
    private String language;
    private String seriesName;
    private String banner;
    private String overview;
    private String firstAired;
    private String imdbId;
    private String zap2ItId;
    private List<String> actors = new ArrayList<String>();
    private String airsDayOfWeek;
    private String airsTime;
    private String contentRating;
    private List<String> genres = new ArrayList<String>();
    private String network;
    private String rating;
    private String runtime;
    private String status;
    private String fanart;
    private String lastUpdated;
    private String poster;

    /**
     * Method getId.
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Method getActors.
     * @return List<String>
     */
    public List<String> getActors() {
        return actors;
    }

    /**
     * Method getAirsDayOfWeek.
     * @return String
     */
    public String getAirsDayOfWeek() {
        return airsDayOfWeek;
    }

    /**
     * Method getAirsTime.
     * @return String
     */
    public String getAirsTime() {
        return airsTime;
    }

    /**
     * Method getBanner.
     * @return String
     */
    public String getBanner() {
        return banner;
    }

    /**
     * Method getContentRating.
     * @return String
     */
    public String getContentRating() {
        return contentRating;
    }

    /**
     * Method getFanart.
     * @return String
     */
    public String getFanart() {
        return fanart;
    }

    /**
     * Method getFirstAired.
     * @return String
     */
    public String getFirstAired() {
        return firstAired;
    }

    /**
     * Method getGenres.
     * @return List<String>
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * Method getImdbId.
     * @return String
     */
    public String getImdbId() {
        return imdbId;
    }

    /**
     * Method getLanguage.
     * @return String
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Method getLastUpdated.
     * @return String
     */
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Method getNetwork.
     * @return String
     */
    public String getNetwork() {
        return network;
    }

    /**
     * Method getOverview.
     * @return String
     */
    public String getOverview() {
        return overview;
    }

    /**
     * Method getPoster.
     * @return String
     */
    public String getPoster() {
        return poster;
    }

    /**
     * Method getRating.
     * @return String
     */
    public String getRating() {
        return rating;
    }

    /**
     * Method getRuntime.
     * @return String
     */
    public String getRuntime() {
        return runtime;
    }

    /**
     * Method getSeriesId.
     * @return String
     */
    public String getSeriesId() {
        return seriesId;
    }

    /**
     * Method getSeriesName.
     * @return String
     */
    public String getSeriesName() {
        return seriesName;
    }

    /**
     * Method getStatus.
     * @return String
     */
    public String getStatus() {
        return status;
    }

    /**
     * Method getZap2ItId.
     * @return String
     */
    public String getZap2ItId() {
        return zap2ItId;
    }

    /**
     * Method setActors.
     * @param actors List<String>
     */
    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    /**
     * Method setAirsDayOfWeek.
     * @param airsDayOfWeek String
     */
    public void setAirsDayOfWeek(String airsDayOfWeek) {
        this.airsDayOfWeek = airsDayOfWeek;
    }

    /**
     * Method setAirsTime.
     * @param airsTime String
     */
    public void setAirsTime(String airsTime) {
        this.airsTime = airsTime;
    }

    /**
     * Method setBanner.
     * @param banner String
     */
    public void setBanner(String banner) {
        this.banner = banner;
    }

    /**
     * Method setContentRating.
     * @param contentRating String
     */
    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    /**
     * Method setFanart.
     * @param fanart String
     */
    public void setFanart(String fanart) {
        this.fanart = fanart;
    }

    /**
     * Method setFirstAired.
     * @param firstAired String
     */
    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
    }

    /**
     * Method setGenres.
     * @param genres List<String>
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * Method setId.
     * @param id String
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method setImdbId.
     * @param imdbId String
     */
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    /**
     * Method setLanguage.
     * @param language String
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Method setLastUpdated.
     * @param lastUpdated String
     */
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Method setNetwork.
     * @param network String
     */
    public void setNetwork(String network) {
        this.network = network;
    }

    /**
     * Method setOverview.
     * @param overview String
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * Method setPoster.
     * @param poster String
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }

    /**
     * Method setRating.
     * @param rating String
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * Method setRuntime.
     * @param runtime String
     */
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    /**
     * Method setSeriesId.
     * @param seriesId Integer
     */
    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    /**
     * Method setSeriesName.
     * @param seriesName String
     */
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    /**
     * Method setStatus.
     * @param status String
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Method setZap2ItId.
     * @param zap2ItId String
     */
    public void setZap2ItId(String zap2ItId) {
        this.zap2ItId = zap2ItId;
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
