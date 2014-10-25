package com.kricko.tvshowupdater.thetvdb;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Series;
import com.kricko.tvshowupdater.utils.Constants;

public class TvDBTests {

//	private static final Logger LOG = LoggerFactory.getLogger(TvDBTests.class);
    
    private final TheTVDBApi tvdb;
//    private static final String LANGUAGE = "he";
//    private static final String TVDBID = "82716";
//    private static final String SERIES_NAME = "90210";
//    private static final String EPISODE_ID = "380752";
//    private static final String SEASON_ID = "34304";
//    private static final String SEASON_YEAR = "2008";

    public TvDBTests() {
        tvdb = new TheTVDBApi(Constants.API_KEY);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testNaming() {
        String seriesName = "Elementary";
        List<Series> seriesList = tvdb.searchSeries(seriesName, null);
        for(Series series:seriesList){
        	System.out.println(String.format("ID %s Name '%s'", series.getId(), series.getSeriesName()));
        }
        assertTrue("Series found for " + seriesName, seriesList.size() > 0);
    }
    
    @Test
    public void testGetEpisode() {
        String seriesId = "255316"; // Elementary Series Id
        int seasonNumber = 1;
        int episodeNumber = 1;
        
        Episode ep = tvdb.getEpisode(seriesId, seasonNumber, episodeNumber, Constants.LANGUAGE);
        System.out.println(String.format("ID %s Name '%s'", ep.getId(), "S"+ep.getSeasonNumber() + "E" + ep.getEpisodeNumber() + ep.getEpisodeName()));
        assertTrue("Episode " + episodeNumber, ep != null);
    }
}
