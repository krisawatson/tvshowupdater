package com.kricko.tvshowupdater.thetvdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.yamj.api.common.http.CommonHttpClient;
import org.yamj.api.common.http.DefaultPoolingHttpClient;

import com.kricko.tvshowupdater.model.Actor;
import com.kricko.tvshowupdater.model.Banners;
import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Series;
import com.kricko.tvshowupdater.model.TVDBUpdates;
import com.kricko.tvshowupdater.utils.Constants;

/**
 * The class <code>TheTVDBApiTest</code> contains tests for the class <code>{@link TheTVDBApi}</code>.
 *
 * @generatedBy CodePro at 28/10/14 09:06
 * @author kris
 * @version $Revision: 1.0 $
 */
public class TheTVDBApiTest {
	/**
	 * Run the TheTVDBApi(String) constructor test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testTheTVDBApi_1()
		throws Exception {
		String apiKey = Constants.API_KEY;

		TheTVDBApi result = new TheTVDBApi(apiKey);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the TheTVDBApi(String,CommonHttpClient) constructor test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testTheTVDBApi_2()
		throws Exception {
		String apiKey = Constants.API_KEY;
		CommonHttpClient httpClient = new DefaultPoolingHttpClient();

		TheTVDBApi result = new TheTVDBApi(apiKey, httpClient);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the TheTVDBApi(String,CommonHttpClient) constructor test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testTheTVDBApi_3()
		throws Exception {
		String apiKey = Constants.API_KEY;
		CommonHttpClient httpClient = new DefaultPoolingHttpClient();

		TheTVDBApi result = new TheTVDBApi(apiKey, httpClient);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Episode getAbsoluteEpisode(String,int,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetAbsoluteEpisode_1()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String seriesId = "";
		int episodeNbr = 1;
		String language = "";

		Episode result = fixture.getAbsoluteEpisode(seriesId, episodeNbr, language);

		assertNotNull(result);
	}

	/**
	 * Run the Episode getAbsoluteEpisode(String,int,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetAbsoluteEpisode_2()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String seriesId = "";
		int episodeNbr = 1;
		String language = null;

		Episode result = fixture.getAbsoluteEpisode(seriesId, episodeNbr, language);

		assertNotNull(result);
	}

	/**
	 * Run the List<Actor> getActors(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetActors_1()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String seriesId = "";

		List<Actor> result = fixture.getActors(seriesId);

		assertNotNull(result);
	}

	/**
	 * Run the List<Episode> getAllEpisodes(String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetAllEpisodes_1()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String id = "";
		String language = "";

		List<Episode> result = fixture.getAllEpisodes(id, language);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the List<Episode> getAllEpisodes(String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetAllEpisodes_2()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String id = "";
		String language = "";

		List<Episode> result = fixture.getAllEpisodes(id, language);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the List<Episode> getAllEpisodes(String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetAllEpisodes_3()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String id = "";
		String language = "";

		List<Episode> result = fixture.getAllEpisodes(id, language);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the String getBannerMirror(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetBannerMirror_1()
		throws Exception {
		String apiKey = Constants.API_KEY;

		String result = TheTVDBApi.getBannerMirror(apiKey);

		// add additional test code here
		assertEquals("http://thetvdb.com/banners/", result);
	}

	/**
	 * Run the Banners getBanners(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetBanners_1()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String seriesId = "";

		Banners result = fixture.getBanners(seriesId);

		assertNotNull(result);
	}

	/**
	 * Run the Episode getDVDEpisode(String,int,int,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetDVDEpisode_1()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String seriesId = "";
		int seasonNbr = 1;
		int episodeNbr = 1;
		String language = "";

		Episode result = fixture.getDVDEpisode(seriesId, seasonNbr, episodeNbr, language);

		assertNotNull(result);
	}

	/**
	 * Run the Episode getDVDEpisode(String,int,int,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetDVDEpisode_2()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String seriesId = "";
		int seasonNbr = 1;
		int episodeNbr = 1;
		String language = null;

		Episode result = fixture.getDVDEpisode(seriesId, seasonNbr, episodeNbr, language);

		assertNotNull(result);
	}

	/**
	 * Run the Episode getEpisode(String,int,int,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetEpisode_1()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String seriesId = "";
		int seasonNbr = 1;
		int episodeNbr = 1;
		String language = "";

		Episode result = fixture.getEpisode(seriesId, seasonNbr, episodeNbr, language);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.getSeasonNumber());
		assertEquals(null, result.getFirstAired());
		assertEquals(null, result.getOverview());
		assertEquals(null, result.getImdbId());
		assertEquals(null, result.getAbsoluteNumber());
		assertEquals(0, result.getAirsAfterSeason());
		assertEquals(null, result.getDvdChapter());
		assertEquals(0, result.getAirsBeforeEpisode());
		assertEquals(0, result.getAirsBeforeSeason());
		assertEquals(null, result.getDvdDiscId());
		assertEquals(null, result.getSeriesId());
		assertEquals(null, result.getFilename());
		assertEquals(null, result.getProductionCode());
		assertEquals(null, result.getSeasonId());
		assertEquals(null, result.getDvdEpisodeNumber());
		assertEquals(null, result.getEpImgFlag());
		assertEquals(null, result.getEpisodeName());
		assertEquals(0, result.getEpisodeNumber());
		assertEquals(null, result.getRating());
		assertEquals(null, result.getCombinedEpisodeNumber());
		assertEquals(null, result.getCombinedSeason());
		assertEquals(null, result.getDvdSeason());
		assertEquals("Episode[id=<null>,combinedEpisodeNumber=<null>,combinedSeason=<null>,dvdChapter=<null>,dvdDiscId=<null>,dvdEpisodeNumber=<null>,dvdSeason=<null>,directors=[],epImgFlag=<null>,episodeName=<null>,episodeNumber=0,firstAired=<null>,guestStars=[],imdbId=<null>,language=<null>,overview=<null>,productionCode=<null>,rating=<null>,seasonNumber=0,writers=[],absoluteNumber=<null>,airsAfterSeason=0,airsBeforeSeason=0,airsBeforeEpisode=0,filename=<null>,lastUpdated=<null>,seriesId=<null>,seasonId=<null>]", result.toString());
		assertEquals(null, result.getLanguage());
		assertEquals(null, result.getId());
		assertEquals(null, result.getLastUpdated());
	}

	/**
	 * Run the Episode getEpisode(String,int,int,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetEpisode_2()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String seriesId = "";
		int seasonNbr = 1;
		int episodeNbr = 1;
		String language = "";

		Episode result = fixture.getEpisode(seriesId, seasonNbr, episodeNbr, language);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.getSeasonNumber());
		assertEquals(null, result.getFirstAired());
		assertEquals(null, result.getOverview());
		assertEquals(null, result.getImdbId());
		assertEquals(null, result.getAbsoluteNumber());
		assertEquals(0, result.getAirsAfterSeason());
		assertEquals(null, result.getDvdChapter());
		assertEquals(0, result.getAirsBeforeEpisode());
		assertEquals(0, result.getAirsBeforeSeason());
		assertEquals(null, result.getDvdDiscId());
		assertEquals(null, result.getSeriesId());
		assertEquals(null, result.getFilename());
		assertEquals(null, result.getProductionCode());
		assertEquals(null, result.getSeasonId());
		assertEquals(null, result.getDvdEpisodeNumber());
		assertEquals(null, result.getEpImgFlag());
		assertEquals(null, result.getEpisodeName());
		assertEquals(0, result.getEpisodeNumber());
		assertEquals(null, result.getRating());
		assertEquals(null, result.getCombinedEpisodeNumber());
		assertEquals(null, result.getCombinedSeason());
		assertEquals(null, result.getDvdSeason());
		assertEquals("Episode[id=<null>,combinedEpisodeNumber=<null>,combinedSeason=<null>,dvdChapter=<null>,dvdDiscId=<null>,dvdEpisodeNumber=<null>,dvdSeason=<null>,directors=[],epImgFlag=<null>,episodeName=<null>,episodeNumber=0,firstAired=<null>,guestStars=[],imdbId=<null>,language=<null>,overview=<null>,productionCode=<null>,rating=<null>,seasonNumber=0,writers=[],absoluteNumber=<null>,airsAfterSeason=0,airsBeforeSeason=0,airsBeforeEpisode=0,filename=<null>,lastUpdated=<null>,seriesId=<null>,seasonId=<null>]", result.toString());
		assertEquals(null, result.getLanguage());
		assertEquals(null, result.getId());
		assertEquals(null, result.getLastUpdated());
	}

	/**
	 * Run the Episode getEpisode(String,int,int,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetEpisode_3()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String seriesId = "";
		int seasonNbr = 1;
		int episodeNbr = 1;
		String language = "";

		Episode result = fixture.getEpisode(seriesId, seasonNbr, episodeNbr, language);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.getSeasonNumber());
		assertEquals(null, result.getFirstAired());
		assertEquals(null, result.getOverview());
		assertEquals(null, result.getImdbId());
		assertEquals(null, result.getAbsoluteNumber());
		assertEquals(0, result.getAirsAfterSeason());
		assertEquals(null, result.getDvdChapter());
		assertEquals(0, result.getAirsBeforeEpisode());
		assertEquals(0, result.getAirsBeforeSeason());
		assertEquals(null, result.getDvdDiscId());
		assertEquals(null, result.getSeriesId());
		assertEquals(null, result.getFilename());
		assertEquals(null, result.getProductionCode());
		assertEquals(null, result.getSeasonId());
		assertEquals(null, result.getDvdEpisodeNumber());
		assertEquals(null, result.getEpImgFlag());
		assertEquals(null, result.getEpisodeName());
		assertEquals(0, result.getEpisodeNumber());
		assertEquals(null, result.getRating());
		assertEquals(null, result.getCombinedEpisodeNumber());
		assertEquals(null, result.getCombinedSeason());
		assertEquals(null, result.getDvdSeason());
		assertEquals("Episode[id=<null>,combinedEpisodeNumber=<null>,combinedSeason=<null>,dvdChapter=<null>,dvdDiscId=<null>,dvdEpisodeNumber=<null>,dvdSeason=<null>,directors=[],epImgFlag=<null>,episodeName=<null>,episodeNumber=0,firstAired=<null>,guestStars=[],imdbId=<null>,language=<null>,overview=<null>,productionCode=<null>,rating=<null>,seasonNumber=0,writers=[],absoluteNumber=<null>,airsAfterSeason=0,airsBeforeSeason=0,airsBeforeEpisode=0,filename=<null>,lastUpdated=<null>,seriesId=<null>,seasonId=<null>]", result.toString());
		assertEquals(null, result.getLanguage());
		assertEquals(null, result.getId());
		assertEquals(null, result.getLastUpdated());
	}

	/**
	 * Run the Episode getEpisode(String,int,int,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetEpisode_4()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String seriesId = "";
		int seasonNbr = 1;
		int episodeNbr = 1;
		String language = "";

		Episode result = fixture.getEpisode(seriesId, seasonNbr, episodeNbr, language);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.getSeasonNumber());
		assertEquals(null, result.getFirstAired());
		assertEquals(null, result.getOverview());
		assertEquals(null, result.getImdbId());
		assertEquals(null, result.getAbsoluteNumber());
		assertEquals(0, result.getAirsAfterSeason());
		assertEquals(null, result.getDvdChapter());
		assertEquals(0, result.getAirsBeforeEpisode());
		assertEquals(0, result.getAirsBeforeSeason());
		assertEquals(null, result.getDvdDiscId());
		assertEquals(null, result.getSeriesId());
		assertEquals(null, result.getFilename());
		assertEquals(null, result.getProductionCode());
		assertEquals(null, result.getSeasonId());
		assertEquals(null, result.getDvdEpisodeNumber());
		assertEquals(null, result.getEpImgFlag());
		assertEquals(null, result.getEpisodeName());
		assertEquals(0, result.getEpisodeNumber());
		assertEquals(null, result.getRating());
		assertEquals(null, result.getCombinedEpisodeNumber());
		assertEquals(null, result.getCombinedSeason());
		assertEquals(null, result.getDvdSeason());
		assertEquals("Episode[id=<null>,combinedEpisodeNumber=<null>,combinedSeason=<null>,dvdChapter=<null>,dvdDiscId=<null>,dvdEpisodeNumber=<null>,dvdSeason=<null>,directors=[],epImgFlag=<null>,episodeName=<null>,episodeNumber=0,firstAired=<null>,guestStars=[],imdbId=<null>,language=<null>,overview=<null>,productionCode=<null>,rating=<null>,seasonNumber=0,writers=[],absoluteNumber=<null>,airsAfterSeason=0,airsBeforeSeason=0,airsBeforeEpisode=0,filename=<null>,lastUpdated=<null>,seriesId=<null>,seasonId=<null>]", result.toString());
		assertEquals(null, result.getLanguage());
		assertEquals(null, result.getId());
		assertEquals(null, result.getLastUpdated());
	}

	/**
	 * Run the Episode getEpisode(String,int,int,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetEpisode_5()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String seriesId = "";
		int seasonNbr = 1;
		int episodeNbr = 1;
		String language = null;

		Episode result = fixture.getEpisode(seriesId, seasonNbr, episodeNbr, language);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.getSeasonNumber());
		assertEquals(null, result.getFirstAired());
		assertEquals(null, result.getOverview());
		assertEquals(null, result.getImdbId());
		assertEquals(null, result.getAbsoluteNumber());
		assertEquals(0, result.getAirsAfterSeason());
		assertEquals(null, result.getDvdChapter());
		assertEquals(0, result.getAirsBeforeEpisode());
		assertEquals(0, result.getAirsBeforeSeason());
		assertEquals(null, result.getDvdDiscId());
		assertEquals(null, result.getSeriesId());
		assertEquals(null, result.getFilename());
		assertEquals(null, result.getProductionCode());
		assertEquals(null, result.getSeasonId());
		assertEquals(null, result.getDvdEpisodeNumber());
		assertEquals(null, result.getEpImgFlag());
		assertEquals(null, result.getEpisodeName());
		assertEquals(0, result.getEpisodeNumber());
		assertEquals(null, result.getRating());
		assertEquals(null, result.getCombinedEpisodeNumber());
		assertEquals(null, result.getCombinedSeason());
		assertEquals(null, result.getDvdSeason());
		assertEquals("Episode[id=<null>,combinedEpisodeNumber=<null>,combinedSeason=<null>,dvdChapter=<null>,dvdDiscId=<null>,dvdEpisodeNumber=<null>,dvdSeason=<null>,directors=[],epImgFlag=<null>,episodeName=<null>,episodeNumber=0,firstAired=<null>,guestStars=[],imdbId=<null>,language=<null>,overview=<null>,productionCode=<null>,rating=<null>,seasonNumber=0,writers=[],absoluteNumber=<null>,airsAfterSeason=0,airsBeforeSeason=0,airsBeforeEpisode=0,filename=<null>,lastUpdated=<null>,seriesId=<null>,seasonId=<null>]", result.toString());
		assertEquals(null, result.getLanguage());
		assertEquals(null, result.getId());
		assertEquals(null, result.getLastUpdated());
	}

	/**
	 * Run the Episode getEpisodeById(String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetEpisodeById_1()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String episodeId = "";
		String language = "";

		Episode result = fixture.getEpisodeById(episodeId, language);

		assertNotNull(result);
	}

	/**
	 * Run the Episode getEpisodeById(String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetEpisodeById_2()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String episodeId = "";
		String language = "";

		Episode result = fixture.getEpisodeById(episodeId, language);

		assertNotNull(result);
	}

	/**
	 * Run the List<Episode> getSeasonEpisodes(String,int,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetSeasonEpisodes_1()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String id = "";
		int season = 1;
		String language = null;

		List<Episode> result = fixture.getSeasonEpisodes(id, season, language);

		assertNotNull(result);
	}

	/**
	 * Run the List<Episode> getSeasonEpisodes(String,int,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetSeasonEpisodes_2()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String id = "";
		int season = 1;
		String language = "";

		List<Episode> result = fixture.getSeasonEpisodes(id, season, language);

		assertNotNull(result);
	}

	/**
	 * Run the String getSeasonYear(String,int,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetSeasonYear_1()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String id = "";
		int seasonNbr = 1;
		String language = "";

		String result = fixture.getSeasonYear(id, seasonNbr, language);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the String getSeasonYear(String,int,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetSeasonYear_2()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String id = "";
		int seasonNbr = 1;
		String language = "";

		String result = fixture.getSeasonYear(id, seasonNbr, language);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the String getSeasonYear(String,int,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetSeasonYear_3()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String id = "";
		int seasonNbr = 1;
		String language = "";

		String result = fixture.getSeasonYear(id, seasonNbr, language);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the String getSeasonYear(String,int,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetSeasonYear_4()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String id = "";
		int seasonNbr = 1;
		String language = "";

		String result = fixture.getSeasonYear(id, seasonNbr, language);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Series getSeries(String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetSeries_1()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String id = "";
		String language = null;

		Series result = fixture.getSeries(id, language);

		assertNotNull(result);
	}

	/**
	 * Run the Series getSeries(String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetSeries_2()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String id = "";
		String language = "";

		Series result = fixture.getSeries(id, language);

		assertNotNull(result);
	}

	/**
	 * Run the TVDBUpdates getWeeklyUpdates() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetWeeklyUpdates_1()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());

		TVDBUpdates result = fixture.getWeeklyUpdates();

		assertNotNull(result);
	}

	/**
	 * Run the String getXmlMirror(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testGetXmlMirror_1()
		throws Exception {
		String apiKey = Constants.API_KEY;

		String result = TheTVDBApi.getXmlMirror(apiKey);

		// add additional test code here
		assertEquals("http://thetvdb.com/api/", result);
	}

	/**
	 * Run the List<Series> searchSeries(String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testSearchSeries_1()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String title = "";
		String language = Constants.LANGUAGE;

		List<Series> result = fixture.searchSeries(title, language);

		assertNotNull(result);
	}

	/**
	 * Run the List<Series> searchSeries(String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testSearchSeries_2()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String title = "";
		String language = Constants.LANGUAGE;

		List<Series> result = fixture.searchSeries(title, language);

		assertNotNull(result);
	}

	/**
	 * Run the List<Series> searchSeries(String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testSearchSeries_3()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String title = "";
		String language = Constants.LANGUAGE;

		List<Series> result = fixture.searchSeries(title, language);

		assertNotNull(result);
	}

	/**
	 * Run the void setProxy(String,int,String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testSetProxy_1()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		String host = "";
		int port = 1;
		String username = "";
		String password = "";

		fixture.setProxy(host, port, username, password);
	}

	/**
	 * Run the void setTimeout(int,int) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception */
	@Test
	public void testSetTimeout_1()
		throws Exception {
		TheTVDBApi fixture = new TheTVDBApi(Constants.API_KEY, new DefaultPoolingHttpClient());
		int webTimeoutConnect = 1;
		int webTimeoutRead = 1;

		fixture.setTimeout(webTimeoutConnect, webTimeoutRead);

	}

	/**
	 * Perform pre-test initialization.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception
	 *         if the initialization fails for some reason */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 * @throws Exception
	 *         if the clean-up fails for some reason */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 28/10/14 09:06
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(TheTVDBApiTest.class);
	}
}