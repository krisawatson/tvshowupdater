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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.yamj.api.common.http.CommonHttpClient;
import org.yamj.api.common.http.DefaultPoolingHttpClient;

import com.kricko.tvshowupdater.model.Actor;
import com.kricko.tvshowupdater.model.Banners;
import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Mirrors;
import com.kricko.tvshowupdater.model.Series;
import com.kricko.tvshowupdater.model.TVDBUpdates;
import com.kricko.tvshowupdater.parser.TvdbParser;
import com.kricko.tvshowupdater.utils.DOMHelper;

/**
 * @author altman.matthew
 * @author stuart.boston
 * @version $Revision: 1.0 $
 */
public class TheTVDBApi {

    private String apiKey = null;
    private CommonHttpClient httpClient;
    private static String xmlMirror = "http://thetvdb.com/api/";
    private static String bannerMirror = "http://thetvdb.com/banners/";
    private static final String XML_EXTENSION = ".xml";
    private static final String SERIES_URL = "/series/";
    private static final String ALL_URL = "/all/";
    private static final String WEEKLY_UPDATES_URL = "/updates/updates_week.xml";
    private static final String URL = "URL: ";

    /**
     * Create an API object with the passed API Key
     *
     * @param apiKey Must be valid
     */
    public TheTVDBApi(String apiKey) {
        // No HttpClient passed, so use a default
        this(apiKey, new DefaultPoolingHttpClient());
    }

    /**
     * Create an API object with the passed API key and using the supplied HttpClient
     *
     * @param apiKey Must not be null or empty
     * @param httpClient
     */
    public TheTVDBApi(String apiKey, CommonHttpClient httpClient) {
        if (StringUtils.isBlank(apiKey)) {
            return;
        }

        this.apiKey = apiKey;
        this.httpClient = httpClient;
        DOMHelper.setHttpClient(this.httpClient);
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
     * Set the web browser proxy information
     *
     * @param host
     * @param port
     * @param username
     * @param password
     */
    public void setProxy(String host, int port, String username, String password) {
        if (httpClient == null) {
            throw new RuntimeException("Failed to set proxy information");
        } else {
            httpClient.setProxy(host, port, username, password);
        }
    }

    /**
     * Set the web browser timeout settings
     *
     * @param webTimeoutConnect
     * @param webTimeoutRead
     */
    public void setTimeout(int webTimeoutConnect, int webTimeoutRead) {
        if (httpClient == null) {
            throw new RuntimeException("Failed to set timeout information");
        } else {
            httpClient.setTimeouts(webTimeoutConnect, webTimeoutRead);
        }
    }

    /**
     * Get the series information
     *
     * @param id
     * @param language
    
     * @return Series
     */
    public Series getSeries(String id, String language) {
        StringBuilder urlBuilder = new StringBuilder();
        try {
            urlBuilder.append(getXmlMirror(apiKey));
            urlBuilder.append(apiKey);
            urlBuilder.append(SERIES_URL);
            urlBuilder.append(id);
            urlBuilder.append("/");
            if (language != null) {
                urlBuilder.append(language).append(XML_EXTENSION);
            }
        } catch (RuntimeException ex) {
            System.out.println(String.format(ex.getMessage(), ex));
            return null;
        }

        List<Series> seriesList = TvdbParser.getSeriesList(urlBuilder.toString(), getBannerMirror(apiKey));
        if (seriesList.isEmpty()) {
            return null;
        } else {
            return seriesList.get(0);
        }
    }

    /**
     * Get all the episodes for a series. Note: This could be a lot of records
     *
     * @param id
     * @param language
    
     * @return List<Episode>
     */
    public List<Episode> getAllEpisodes(String id, String language) {
        List<Episode> episodeList = Collections.emptyList();

        if (isValidNumber(id)) {
            StringBuilder urlBuilder = new StringBuilder();
            try {
                urlBuilder.append(getXmlMirror(apiKey));
            } catch (RuntimeException ex) {
                System.out.println(String.format(ex.getMessage(), ex));
                urlBuilder.append("http://thetvdb.com/api/");
            }
            urlBuilder.append(apiKey);
            urlBuilder.append(SERIES_URL);
            urlBuilder.append(id);
            urlBuilder.append(ALL_URL);
            if (StringUtils.isNotBlank(language)) {
                urlBuilder.append(language).append(XML_EXTENSION);
            }

            episodeList = TvdbParser.getAllEpisodes(urlBuilder.toString(), -1, getBannerMirror(apiKey));
        }
        return episodeList;
    }

    /**
     * Get all the episodes from a specific season for a series. Note: This could be a lot of records
     *
     * @param id
     * @param season
     * @param language
    
     * @return List<Episode>
     */
    public List<Episode> getSeasonEpisodes(String id, int season, String language) {
        StringBuilder urlBuilder = new StringBuilder();
        try {
            urlBuilder.append(getXmlMirror(apiKey));
            urlBuilder.append(apiKey);
            urlBuilder.append(SERIES_URL);
            urlBuilder.append(id);
            urlBuilder.append(ALL_URL);
            if (language != null) {
                urlBuilder.append(language).append(XML_EXTENSION);
            }
        } catch (RuntimeException ex) {
            System.out.println(String.format(ex.getMessage(), ex));
            return null;
        }

        List<Episode> episodeList = TvdbParser.getAllEpisodes(urlBuilder.toString(), season, getBannerMirror(apiKey));
        if (episodeList.isEmpty()) {
            return null;
        } else {
            return episodeList;
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

        StringBuilder url = null;
        
    	try{
    		url = urlBuilder(seriesId, "/default/", seasonNbr, episodeNbr, language);
    	} catch (Exception ex){
    		return new Episode();
    	}

        return TvdbParser.getEpisode(url.toString(), getBannerMirror(apiKey));
    }

    /**
     * Get a specific DVD episode's information
     *
     * @param seriesId
     * @param seasonNbr
     * @param episodeNbr
     * @param language
    
     * @return Episode
     */
    public Episode getDVDEpisode(String seriesId, int seasonNbr, int episodeNbr, String language) {
    	StringBuilder url = null;
    
    	try{
    		url = urlBuilder(seriesId, "/dvd/", seasonNbr, episodeNbr, language);
    	} catch (Exception ex){
    		return new Episode();
    	}

        return TvdbParser.getEpisode(url.toString(), getBannerMirror(apiKey));
    }

    /**
     * Get a specific absolute episode's information
     *
     * @param seriesId
     * @param episodeNbr
     * @param language
    
     * @return Episode
     */
    public Episode getAbsoluteEpisode(String seriesId, int episodeNbr, String language) {
        StringBuilder urlBuilder = new StringBuilder();
        try {
            urlBuilder.append(getXmlMirror(apiKey));
            urlBuilder.append(apiKey);
            urlBuilder.append(SERIES_URL);
            urlBuilder.append(seriesId);
            urlBuilder.append("/absolute/");
            urlBuilder.append(episodeNbr);
            urlBuilder.append("/");
            if (language != null) {
                urlBuilder.append(language).append(XML_EXTENSION);
            }
        } catch (RuntimeException ex) {
            System.out.println(String.format(ex.getMessage(), ex));
            return new Episode();
        }

        return TvdbParser.getEpisode(urlBuilder.toString(), getBannerMirror(apiKey));
    }

    /**
     * Get a list of banners for the series id
     *
     * @param id
     * @param seasonNbr
     * @param language
    
     * @return String
     */
    public String getSeasonYear(String id, int seasonNbr, String language) {
        String year = null;

        Episode episode = getEpisode(id, seasonNbr, 1, language);
        if (episode != null && StringUtils.isNotBlank(episode.getFirstAired())) {
            Date date;

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                date = dateFormat.parse(episode.getFirstAired());
            } catch (ParseException ex) {
                System.out.println(String.format("Failed to transform date: " + episode.getFirstAired(), ex));
                date = null;
            }

            if (date != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                year = String.valueOf(cal.get(Calendar.YEAR));
            }
        }

        return year;
    }

    /**
     * Method getBanners.
     * @param seriesId String
     * @return Banners
     */
    public Banners getBanners(String seriesId) {
        StringBuilder urlBuilder = new StringBuilder();
        try {
            urlBuilder.append(getXmlMirror(apiKey));
            urlBuilder.append(apiKey);
            urlBuilder.append(SERIES_URL);
            urlBuilder.append(seriesId);
            urlBuilder.append("/banners.xml");
        } catch (RuntimeException ex) {
            System.out.println(String.format(ex.getMessage(), ex));
            return new Banners();
        }

        return TvdbParser.getBanners(urlBuilder.toString(), getBannerMirror(apiKey));
    }

    /**
     * Get a list of actors from the series id
     *
     * @param seriesId
    
     * @return List<Actor>
     */
    public List<Actor> getActors(String seriesId) {
        StringBuilder urlBuilder = new StringBuilder();
        try {
            urlBuilder.append(getXmlMirror(apiKey));
            urlBuilder.append(apiKey);
            urlBuilder.append(SERIES_URL);
            urlBuilder.append(seriesId);
            urlBuilder.append("/actors.xml");
        } catch (RuntimeException ex) {
            System.out.println(String.format(ex.getMessage(), ex));
            return new ArrayList<Actor>();
        }

        System.out.println(String.format(URL + urlBuilder.toString()));
        return TvdbParser.getActors(urlBuilder.toString(), getBannerMirror(apiKey));
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
            System.out.println(String.format("Failed to encode title: " + title, ex));
            // Try and use the raw title
            urlBuilder.append(title);
        } catch (RuntimeException ex) {
            System.out.println(String.format(ex.getMessage(), ex));
            return new ArrayList<Series>();
        }

        return TvdbParser.getSeriesList(urlBuilder.toString(), getBannerMirror(apiKey));
    }

    /**
     * Get information for a specific episode
     *
     * @param episodeId
     * @param language
    
     * @return Episode
     */
    public Episode getEpisodeById(String episodeId, String language) {
        StringBuilder urlBuilder = new StringBuilder();

        try {
            urlBuilder.append(getXmlMirror(apiKey));
            urlBuilder.append(apiKey);
            urlBuilder.append("/episodes/");
            urlBuilder.append(episodeId);
            urlBuilder.append("/");
            if (StringUtils.isNotBlank(language)) {
                urlBuilder.append(language);
                urlBuilder.append(XML_EXTENSION);
            }
        } catch (RuntimeException ex) {
            System.out.println(String.format(ex.getMessage(), ex));
            return new Episode();
        }

        return TvdbParser.getEpisode(urlBuilder.toString(), getBannerMirror(apiKey));
    }

    /**
     * Method getWeeklyUpdates.
     * @return TVDBUpdates
     */
    public TVDBUpdates getWeeklyUpdates() {
        StringBuilder urlBuilder = new StringBuilder();

        urlBuilder.append(getXmlMirror(apiKey));
        urlBuilder.append(apiKey);
        urlBuilder.append(WEEKLY_UPDATES_URL);

        return TvdbParser.getUpdates(urlBuilder.toString());
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
            System.out.println(String.format(ex.getMessage(), ex));
            throw ex;
        }
        return urlBuilder;
    }
}
