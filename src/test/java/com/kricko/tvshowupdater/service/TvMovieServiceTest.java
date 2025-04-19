package com.kricko.tvshowupdater.service;

import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Series;
import com.kricko.tvshowupdater.thetvdb.TheTVDBApi;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Execution(ExecutionMode.CONCURRENT)
@ResourceLock("TvMovieServiceTest")
class TvMovieServiceTest {

    @Mock
    private TheTVDBApi tvdb;

    private TvMovieService tvMovieService;
    private static final String seriesName = "Test";
    private Path testRoot;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        
        // Mock the TheTVDBApi responses
        Series mockSeries = new Series();
        mockSeries.setId("1234");
        mockSeries.setSeriesName(seriesName);
        
        Episode mockEpisode = new Episode();
        mockEpisode.setSeasonNumber(1);
        mockEpisode.setEpisodeNumber(2);
        mockEpisode.setEpisodeName("Test Episode");
        
        when(tvdb.searchSeries(anyString(), anyString())).thenReturn(Arrays.asList(mockSeries));
        when(tvdb.getEpisode(anyString(), anyInt(), anyInt(), anyString())).thenReturn(mockEpisode);
        
        tvMovieService = new TvMovieService(tvdb);
        
        // Create test root directory
        testRoot = Files.createTempDirectory("tvshow_test");
        
        // Clean up test files on exit
        testRoot.toFile().deleteOnExit();
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up test files after each test
        Files.walk(testRoot)
             .sorted((a, b) -> b.compareTo(a)) // Reverse order to delete files before directories
             .forEach(path -> {
                 try {
                     Files.delete(path);
                 } catch (IOException e) {
                     // Ignore deletion errors
                 }
             });
    }

    @Test
    @DisplayName("Test testRefactorFilesAddTitleSeason3 with valid data")
    public void testRefactorFilesAddTitleSeason3() throws IOException {
        // Create test files
        Path seasonDir = Files.createDirectories(testRoot.resolve("Season 3"));
        Path file1 = Files.createFile(seasonDir.resolve("S03E05.mkv"));
        Path file2 = Files.createFile(seasonDir.resolve("S03E06.mkv"));
        List<Path> files = Arrays.asList(file1, file2);
        Optional<String> seriesId = Optional.of("1234");

        List<String> removableFolders = tvMovieService.refactorFilesAddTitle(seriesName, files, testRoot.toString(), seriesId);
        assertTrue(removableFolders.isEmpty());
    }

    @Test
    @DisplayName("Test identifyPotentialMissingEpisodes Season 1 with valid data returns single episode")
    void testIdentifyPotentialMissingEpisodes() throws IOException {
        // Create test directory and files
        Path seasonDir = Files.createDirectories(testRoot.resolve("Season 1"));
        Files.createFile(seasonDir.resolve("S01E01.mkv"));
        Files.createFile(seasonDir.resolve("S01E02.mkv"));
        Files.createFile(seasonDir.resolve("S01E04.mkv"));
        Files.createFile(seasonDir.resolve("S01E05.mkv"));

        // Call the method under test
        List<String> result = tvMovieService.identifyPotentialMissingEpisodes(seasonDir, emptyList());

        // Assert the result
        assertEquals(1, result.size());
        MatcherAssert.assertThat(result.get(0), containsString("- Season 1, Episodes [3]"));
    }

    @Test
    @DisplayName("Test identifyPotentialMissingEpisodes Season 2 with valid data returns multiple episodes")
    void testIdentifyPotentialMissingEpisodesMultipleEpisodes() throws IOException {
        // Create test directory and files
        Path seasonDir = Files.createDirectories(testRoot.resolve("Season 2"));
        Files.createFile(seasonDir.resolve("S02E01.mkv"));
        Files.createFile(seasonDir.resolve("S02E02.mkv"));
        Files.createFile(seasonDir.resolve("S02E06.mkv"));
        Files.createFile(seasonDir.resolve("S02E07.mkv"));

        // Call the method under test
        List<String> result = tvMovieService.identifyPotentialMissingEpisodes(seasonDir, emptyList());

        // Assert the result
        assertEquals(1, result.size());
        MatcherAssert.assertThat(result.get(0), containsString("- Season 2, Episodes [3, 4, 5]"));
    }
}