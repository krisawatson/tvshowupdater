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

/**
 */
public class BannerUpdate implements Serializable {

    // Default serial UID
    private static final long serialVersionUID = 1L;
    private String seasonNum;
    private String series;
    private String format;
    private String language;
    private String path;
    private String time;
    private String type;

    /**
     * Method getSeasonNum.
     * @return String
     */
    public String getSeasonNum() {
        return seasonNum;
    }

    /**
     * Method getSeries.
     * @return String
     */
    public String getSeries() {
        return series;
    }

    /**
     * Method getFormat.
     * @return String
     */
    public String getFormat() {
        return format;
    }

    /**
     * Method getLanguage.
     * @return String
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Method getPath.
     * @return String
     */
    public String getPath() {
        return path;
    }

    /**
     * Method getTime.
     * @return String
     */
    public String getTime() {
        return time;
    }

    /**
     * Method getType.
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Method setSeasonNum.
     * @param seasonNum String
     */
    public void setSeasonNum(String seasonNum) {
        this.seasonNum = seasonNum;
    }

    /**
     * Method setSeries.
     * @param series String
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     * Method setFormat.
     * @param format String
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Method setLanguage.
     * @param language String
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Method setPath.
     * @param path String
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Method setTime.
     * @param time String
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Method setType.
     * @param type String
     */
    public void setType(String type) {
        this.type = type;
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