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
package com.kricko.tvshowupdater.thetvdb;

import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Mirrors;
import com.kricko.tvshowupdater.model.Series;
import com.kricko.tvshowupdater.parser.TvdbParser;
import com.kricko.tvshowupdater.utils.DOMHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author altman.matthew
 * @author stuart.boston
 * @version $Revision: 1.0 $
 */
@Slf4j
public class TheTVDBApi {

    private String apiKey = null;
    private static String xmlMirror = "https://thetvdb.com/api/";
    private static String bannerMirror = "https://thetvdb.com/banners/";
    private static final String XML_EXTENSION = ".xml";
    private static final String SERIES_URL = "/series/";

    /**
     * Create an API object with the passed API Key
     *
     * @param apiKey Must be valid
     */
    public TheTVDBApi(String apiKey) {
        // No HttpClient passed, so use a default
        this(apiKey, HttpClient.newHttpClient());
    }

    /**
     * Create an API object with the passed API key and using the supplied HttpClient
     *
     * @param apiKey Must not be null or empty
     */
    public TheTVDBApi(String apiKey, HttpClient httpClient) {
        if (StringUtils.isBlank(apiKey)) {
            return;
        }

        this.apiKey = apiKey;
        DOMHelper.setHttpClient(httpClient);
    }

    /**
     * Get the mirror information from TheTVDb
     *
    
     * @param apiKey String
     */
    private static void getMirrors(String apiKey) {
        // If we don't need to get the mirrors, then just return
        if (xmlMirror != null && bannerMirror != null) {
            return;
        }

        Mirrors mirrors = new Mirrors(apiKey);
        xmlMirror = mirrors.getMirror(Mirrors.TYPE_XML);
        bannerMirror = mirrors.getMirror(Mirrors.TYPE_BANNER);

        if (xmlMirror == null) {
            throw new RuntimeException("There is a problem getting the xmlMirror data from TheTVDB, this means it is likely to be down.");
        } else {
            xmlMirror += "/api/";
        }

        if (bannerMirror == null) {
            throw new RuntimeException("There is a problem getting the bannerMirror data from TheTVDB, this means it is likely to be down.");
        } else {
            bannerMirror += "/banners/";
        }
    }

    /**
     * Get a specific episode's information
     *
     * @param seriesId
     * @param seasonNbr
     * @param episodeNbr
     * @param language
    
     * @return Episode
     */
    public Episode getEpisode(String seriesId, int seasonNbr, int episodeNbr, String language) {
        if (!isValidNumber(seriesId) || !isValidNumber(seasonNbr) || !isValidNumber(episodeNbr)) {
            // Invalid number passed
            return new Episode();
        }

        StringBuilder url;
        
    	try{
    		url = urlBuilder(seriesId, "/default/", seasonNbr, episodeNbr, language);
    	} catch (Exception ex){
    		return new Episode();
    	}

        return TvdbParser.getEpisode(url.toString(), getBannerMirror(apiKey));
    }

    /**
     * Method searchSeries.
     * @param title String
     * @param language String
     * @return List<Series>
     */
    public List<Series> searchSeries(String title, String language) {
        StringBuilder urlBuilder = new StringBuilder();

        try {
            urlBuilder.append(getXmlMirror(apiKey));
            urlBuilder.append("GetSeries.php?seriesname=");
            urlBuilder.append(URLEncoder.encode(title, "UTF-8"));
            if (language != null) {
                urlBuilder.append("&language=").append(language);
            }
        } catch (UnsupportedEncodingException ex) {
            log.info("Failed to encode title: {}", title, ex);
            // Try and use the raw title
            urlBuilder.append(title);
        } catch (RuntimeException ex) {
            log.info("Failed to encode title: {}", title, ex);
            return new ArrayList<>();
        }

        return TvdbParser.getSeriesList(urlBuilder.toString(), getBannerMirror(apiKey));
    }

    /**
     * Get the XML Mirror URL
     *
     * @param apiKey
    
     * @return String
     */
    public static String getXmlMirror(String apiKey) {
        // Force a load of the mirror information if it doesn't exist
        getMirrors(apiKey);
        return xmlMirror;
    }

    /**
     * Get the Banner Mirror URL
     *
     * @param apiKey
    
     * @return String
     */
    public static String getBannerMirror(String apiKey) {
        // Force a load of the mirror information if it doesn't exist
        getMirrors(apiKey);
        return bannerMirror;
    }

    /**
     * Method isValidNumber.
     * @param number String
     * @return boolean
     */
    private boolean isValidNumber(String number) {
        return StringUtils.isNumeric(number);
    }

    /**
     * Method isValidNumber.
     * @param number int
     * @return boolean
     */
    private boolean isValidNumber(int number) {
        return (number >= 0);
    }
    
    private StringBuilder urlBuilder(String seriesId, String postfix, int seasonNbr,
    		int episodeNbr, String language) throws Exception{
    	if (!isValidNumber(seriesId) || !isValidNumber(seasonNbr) || !isValidNumber(episodeNbr)) {
            throw new Exception("Invalid number exceptions");
        }
    	
    	StringBuilder urlBuilder = new StringBuilder();
        try {
            urlBuilder.append(getXmlMirror(apiKey));
            urlBuilder.append(apiKey);
            urlBuilder.append(SERIES_URL);
            urlBuilder.append(seriesId);
            urlBuilder.append(postfix);
            urlBuilder.append(seasonNbr);
            urlBuilder.append("/");
            urlBuilder.append(episodeNbr);
            urlBuilder.append("/");
            if (language != null) {
                urlBuilder.append(language).append(XML_EXTENSION);
            }
        } catch (RuntimeException ex) {
            log.error("Failed to build URL", ex);
            throw ex;
        }
        return urlBuilder;
    }
}
