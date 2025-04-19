package com.kricko.tvshowupdater.thetvdb;

import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Series;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("unchecked")
class TheTVDBApiTest {

    private static final String API_KEY = "test-api-key";

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse<String> httpResponse;

    private TheTVDBApi tvdbApi;

    @BeforeEach
    void setUp() {
        tvdbApi = new TheTVDBApi(API_KEY, httpClient);
    }

    @Test
    @DisplayName("Should search for series")
    void shouldSearchForSeries() throws IOException, InterruptedException, URISyntaxException {
        // Arrange
        String searchResponse = """
            <?xml version="1.0" encoding="UTF-8" ?>
            <Data>
                <Series>
                    <id>123</id>
                    <SeriesName>Test Show</SeriesName>
                    <Overview>A test show</Overview>
                    <FirstAired>2020-01-01</FirstAired>
                    <banner>path/to/banner.jpg</banner>
                </Series>
            </Data>
            """;
        when(httpClient.send(any(HttpRequest.class), any(BodyHandler.class))).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn(searchResponse);

        // Act
        List<Series> results = tvdbApi.searchSeries("Test Show", "en");

        // Assert
        assertThat(results)
            .isNotEmpty()
            .hasSize(1)
            .first()
            .satisfies(series -> {
                assertThat(series.getId()).isEqualTo("123");
                assertThat(series.getSeriesName()).isEqualTo("Test Show");
                assertThat(series.getOverview()).isEqualTo("A test show");
                assertThat(series.getFirstAired()).isEqualTo("2020-01-01");
                assertThat(series.getBanner()).isEqualTo("https://thetvdb.com/banners/path/to/banner.jpg");
            });
    }

    @Test
    @DisplayName("Should get episode details")
    void shouldGetEpisodeDetails() throws IOException, InterruptedException, URISyntaxException {
        // Arrange
        String episodeResponse = """
            <?xml version="1.0" encoding="UTF-8" ?>
            <Data>
                <Episode>
                    <id>456</id>
                    <SeasonNumber>1</SeasonNumber>
                    <EpisodeNumber>1</EpisodeNumber>
                    <EpisodeName>Pilot</EpisodeName>
                    <FirstAired>2020-01-01</FirstAired>
                    <Overview>First episode</Overview>
                </Episode>
            </Data>
            """;
        when(httpClient.send(any(HttpRequest.class), any(BodyHandler.class))).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn(episodeResponse);

        // Act
        Episode episode = tvdbApi.getEpisode("123", 1, 1, "en");

        // Assert
        assertThat(episode)
            .isNotNull()
            .satisfies(ep -> {
                assertThat(ep.getId()).isEqualTo("456");
                assertThat(ep.getSeasonNumber()).isEqualTo(1);
                assertThat(ep.getEpisodeNumber()).isEqualTo(1);
                assertThat(ep.getEpisodeName()).isEqualTo("Pilot");
                assertThat(ep.getFirstAired()).isEqualTo("2020-01-01");
                assertThat(ep.getOverview()).isEqualTo("First episode");
            });
    }

    @Test
    @DisplayName("Should handle invalid series search")
    void shouldHandleInvalidSeriesSearch() throws IOException, InterruptedException, URISyntaxException {
        // Arrange
        String searchResponse = """
            <?xml version="1.0" encoding="UTF-8" ?>
            <Data>
            </Data>
            """;
        when(httpClient.send(any(HttpRequest.class), any(BodyHandler.class))).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn(searchResponse);

        // Act
        List<Series> results = tvdbApi.searchSeries("NonExistentShow", "en");

        // Assert
        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("Should handle invalid episode request")
    void shouldHandleInvalidEpisodeRequest() {
        // Act
        Episode episode = tvdbApi.getEpisode("-1", -1, -1, "en");

        // Assert
        assertThat(episode).isNotNull();
        assertThat(episode.getId()).isNull();
    }
}
