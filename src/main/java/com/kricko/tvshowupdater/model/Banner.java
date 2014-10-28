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
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author altman.matthew
 * @version $Revision: 1.0 $
 */
public class Banner implements Serializable {

    // Default serial UID
    private static final long serialVersionUID = 1L;
    private int id;
    private String url;
    private BannerListType bannerType;
    private BannerType bannerType2;
    private String colours;
    private Float rating;
    private int ratingCount;
    private String language;
    private boolean seriesName;
    private String thumb;
    private String vignette;
    private int season = 0;

    /**
     * Method getId.
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Method getBannerType.
     * @return BannerListType
     */
    public BannerListType getBannerType() {
        return bannerType;
    }

    /**
     * Method getBannerType2.
     * @return BannerType
     */
    public BannerType getBannerType2() {
        return bannerType2;
    }

    /**
     * Method getRating.
     * @return Float
     */
    public Float getRating() {
        return rating;
    }

    /**
     * Method getRatingCount.
     * @return int
     */
    public int getRatingCount() {
        return ratingCount;
    }

    /**
     * Method getColours.
     * @return String
     */
    public String getColours() {
        return colours;
    }

    /**
     * Method getLanguage.
     * @return String
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Method getSeason.
     * @return int
     */
    public int getSeason() {
        return season;
    }

    /**
     * Method isSeriesName.
     * @return boolean
     */
    public boolean isSeriesName() {
        return seriesName;
    }

    /**
     * Method getThumb.
     * @return String
     */
    public String getThumb() {
        return thumb;
    }

    /**
     * Method getUrl.
     * @return String
     */
    public String getUrl() {
        return url;
    }

    /**
     * Method getVignette.
     * @return String
     */
    public String getVignette() {
        return vignette;
    }

    /**
     * Method setUrl.
     * @param url String
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Method setVignette.
     * @param vignette String
     */
    public void setVignette(String vignette) {
        this.vignette = vignette;
    }

    /**
     * Method setThumb.
     * @param thumb String
     */
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    /**
     * Method setLanguage.
     * @param language String
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Method setSeason.
     * @param season int
     */
    public void setSeason(int season) {
        this.season = season;
    }

    /**
     * Method setSeason.
     * @param season String
     */
    public void setSeason(String season) {
        this.season = NumberUtils.toInt(season, 0);
    }

    /**
     * Method setBannerType.
     * @param bannerType BannerListType
     */
    public void setBannerType(BannerListType bannerType) {
        this.bannerType = bannerType;
    }

    /**
     * Method setBannerType2.
     * @param bannerType2 BannerType
     */
    public void setBannerType2(BannerType bannerType2) {
        this.bannerType2 = bannerType2;
    }

    /**
     * Method setId.
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method setId.
     * @param id String
     */
    public void setId(String id) {
        this.id = NumberUtils.toInt(id, 0);
    }

    /**
     * Method setColours.
     * @param colours String
     */
    public void setColours(String colours) {
        this.colours = colours;
    }

    /**
     * Method setRating.
     * @param rating String
     */
    public void setRating(String rating) {
        this.rating = NumberUtils.toFloat(rating, 0f);
    }

    /**
     * Method setRatingCount.
     * @param ratingCount String
     */
    public void setRatingCount(String ratingCount) {
        this.ratingCount = NumberUtils.toInt(ratingCount, 0);
    }

    /**
     * Method setSeriesName.
     * @param seriesName boolean
     */
    public void setSeriesName(boolean seriesName) {
        this.seriesName = seriesName;
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
