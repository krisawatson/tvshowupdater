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
public class Episode implements Serializable {

    // Default serial UID
    private static final long serialVersionUID = 1L;
    private String id;
    private String combinedEpisodeNumber;
    private String combinedSeason;
    private String dvdChapter;
    private String dvdDiscId;
    private String dvdEpisodeNumber;
    private String dvdSeason;
    private List<String> directors = new ArrayList<String>();
    private String epImgFlag;
    private String episodeName;
    private int episodeNumber;
    private String firstAired;
    private List<String> guestStars = new ArrayList<String>();
    private String imdbId;
    private String language;
    private String overview;
    private String productionCode;
    private String rating;
    private int seasonNumber;
    private List<String> writers = new ArrayList<String>();
    private String absoluteNumber;
    private int airsAfterSeason;
    private int airsBeforeSeason;
    private int airsBeforeEpisode;
    private String filename;
    private String lastUpdated;
    private String seriesId;
    private String seasonId;

    /**
     * Method getId.
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Method getAbsoluteNumber.
     * @return String
     */
    public String getAbsoluteNumber() {
        return absoluteNumber;
    }

    /**
     * Method getAirsAfterSeason.
     * @return int
     */
    public int getAirsAfterSeason() {
        return airsAfterSeason;
    }

    /**
     * Method getAirsBeforeEpisode.
     * @return int
     */
    public int getAirsBeforeEpisode() {
        return airsBeforeEpisode;
    }

    /**
     * Method getAirsBeforeSeason.
     * @return int
     */
    public int getAirsBeforeSeason() {
        return airsBeforeSeason;
    }

    /**
     * Method getCombinedEpisodeNumber.
     * @return String
     */
    public String getCombinedEpisodeNumber() {
        return combinedEpisodeNumber;
    }

    /**
     * Method getCombinedSeason.
     * @return String
     */
    public String getCombinedSeason() {
        return combinedSeason;
    }

    /**
     * Method getDirectors.
     * @return List<String>
     */
    public List<String> getDirectors() {
        return directors;
    }

    /**
     * Method getDvdChapter.
     * @return String
     */
    public String getDvdChapter() {
        return dvdChapter;
    }

    /**
     * Method getDvdDiscId.
     * @return String
     */
    public String getDvdDiscId() {
        return dvdDiscId;
    }

    /**
     * Method getDvdEpisodeNumber.
     * @return String
     */
    public String getDvdEpisodeNumber() {
        return dvdEpisodeNumber;
    }

    /**
     * Method getDvdSeason.
     * @return String
     */
    public String getDvdSeason() {
        return dvdSeason;
    }

    /**
     * Method getEpImgFlag.
     * @return String
     */
    public String getEpImgFlag() {
        return epImgFlag;
    }

    /**
     * Method getEpisodeName.
     * @return String
     */
    public String getEpisodeName() {
        return episodeName;
    }

    /**
     * Method getEpisodeNumber.
     * @return int
     */
    public int getEpisodeNumber() {
        return episodeNumber;
    }

    /**
     * Method getFilename.
     * @return String
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Method getFirstAired.
     * @return String
     */
    public String getFirstAired() {
        return firstAired;
    }

    /**
     * Method getGuestStars.
     * @return List<String>
     */
    public List<String> getGuestStars() {
        return guestStars;
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
     * Method getOverview.
     * @return String
     */
    public String getOverview() {
        return overview;
    }

    /**
     * Method getProductionCode.
     * @return String
     */
    public String getProductionCode() {
        return productionCode;
    }

    /**
     * Method getRating.
     * @return String
     */
    public String getRating() {
        return rating;
    }

    /**
     * Method getSeasonId.
     * @return String
     */
    public String getSeasonId() {
        return seasonId;
    }

    /**
     * Method getSeasonNumber.
     * @return int
     */
    public int getSeasonNumber() {
        return seasonNumber;
    }

    /**
     * Method getSeriesId.
     * @return String
     */
    public String getSeriesId() {
        return seriesId;
    }

    /**
     * Method getWriters.
     * @return List<String>
     */
    public List<String> getWriters() {
        return writers;
    }

    /**
     * Method setAbsoluteNumber.
     * @param absoluteNumber String
     */
    public void setAbsoluteNumber(String absoluteNumber) {
        this.absoluteNumber = absoluteNumber;
    }

    /**
     * Method setAirsAfterSeason.
     * @param airsAfterSeason int
     */
    public void setAirsAfterSeason(int airsAfterSeason) {
        this.airsAfterSeason = airsAfterSeason;
    }

    /**
     * Method setAirsBeforeEpisode.
     * @param airsBeforeEpisode int
     */
    public void setAirsBeforeEpisode(int airsBeforeEpisode) {
        this.airsBeforeEpisode = airsBeforeEpisode;
    }

    /**
     * Method setAirsBeforeSeason.
     * @param airsBeforeSeason int
     */
    public void setAirsBeforeSeason(int airsBeforeSeason) {
        this.airsBeforeSeason = airsBeforeSeason;
    }

    /**
     * Method setCombinedEpisodeNumber.
     * @param combinedEpisodeNumber String
     */
    public void setCombinedEpisodeNumber(String combinedEpisodeNumber) {
        this.combinedEpisodeNumber = combinedEpisodeNumber;
    }

    /**
     * Method setCombinedSeason.
     * @param combinedSeason String
     */
    public void setCombinedSeason(String combinedSeason) {
        this.combinedSeason = combinedSeason;
    }

    /**
     * Method setDirectors.
     * @param directors List<String>
     */
    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    /**
     * Method setDvdChapter.
     * @param dvdChapter String
     */
    public void setDvdChapter(String dvdChapter) {
        this.dvdChapter = dvdChapter;
    }

    /**
     * Method setDvdDiscId.
     * @param dvdDiscId String
     */
    public void setDvdDiscId(String dvdDiscId) {
        this.dvdDiscId = dvdDiscId;
    }

    /**
     * Method setDvdEpisodeNumber.
     * @param dvdEpisodeNumber String
     */
    public void setDvdEpisodeNumber(String dvdEpisodeNumber) {
        this.dvdEpisodeNumber = dvdEpisodeNumber;
    }

    /**
     * Method setDvdSeason.
     * @param dvdSeason String
     */
    public void setDvdSeason(String dvdSeason) {
        this.dvdSeason = dvdSeason;
    }

    /**
     * Method setEpImgFlag.
     * @param epImgFlag String
     */
    public void setEpImgFlag(String epImgFlag) {
        this.epImgFlag = epImgFlag;
    }

    /**
     * Method setEpisodeName.
     * @param episodeName String
     */
    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    /**
     * Method setEpisodeNumber.
     * @param episodeNumber int
     */
    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    /**
     * Method setFilename.
     * @param filename String
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Method setFirstAired.
     * @param firstAired String
     */
    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
    }

    /**
     * Method setGuestStars.
     * @param guestStars List<String>
     */
    public void setGuestStars(List<String> guestStars) {
        this.guestStars = guestStars;
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
     * Method setOverview.
     * @param overview String
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * Method setProductionCode.
     * @param productionCode String
     */
    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    /**
     * Method setRating.
     * @param rating String
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * Method setSeasonId.
     * @param seasonId String
     */
    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }

    /**
     * Method setSeasonNumber.
     * @param seasonNumber int
     */
    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    /**
     * Method setSeriesId.
     * @param seriesId String
     */
    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    /**
     * Method setWriters.
     * @param writers List<String>
     */
    public void setWriters(List<String> writers) {
        this.writers = writers;
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
