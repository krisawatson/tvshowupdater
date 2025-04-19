package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Series;
import com.kricko.tvshowupdater.service.TvMovieService;
import com.kricko.tvshowupdater.thetvdb.TheTVDBApi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class RefactorFilesTest {

    @Mock
    private TheTVDBApi tvdb;

    private TvMovieService tvMovieService;
    private String seriesName;
    private Path parentDir;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        seriesName = "Test Series";
        
        // Mock the TheTVDBApi responses
        Series mockSeries = new Series();
        mockSeries.setId("1234");
        mockSeries.setSeriesName(seriesName);
        
        Episode mockEpisode = new Episode();
        mockEpisode.setEpisodeName("Test Episode");
        
        when(tvdb.searchSeries(anyString(), anyString())).thenReturn(Arrays.asList(mockSeries));
        when(tvdb.getEpisode(anyString(), anyInt(), anyInt(), anyString())).thenReturn(mockEpisode);
        
        tvMovieService = new TvMovieService(tvdb);
        parentDir = Files.createTempDirectory("tvshow_test");
        parentDir.toFile().deleteOnExit();
    }
    
    @AfterEach
    void tearDown() throws IOException {
        // Clean up test files after each test
        Files.walk(parentDir)
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
    void testRefactorFilesAddTitleSeason1() throws IOException {
        // Setup test data
        Path seasonDir = Files.createDirectories(parentDir.resolve("Season 1"));
        Path file = Files.createFile(seasonDir.resolve("S01E01.mkv"));

        // Get all video files
        List<Path> files = tvMovieService.getMovieFiles(seasonDir);
        assertNotNull(files);
        assertEquals(1, files.size());

        // Refactor files
        List<String> removableFolders = tvMovieService.refactorFilesAddTitle(seriesName, files, parentDir.toString(), Optional.empty());
        assertTrue(removableFolders.isEmpty());
    }

    @Test
    void testRefactorFilesAddTitleSeason2() throws IOException {
        // Setup test data
        Path seasonDir = Files.createDirectories(parentDir.resolve("Season 2"));
        Path file = Files.createFile(seasonDir.resolve("S02E01.mkv"));

        // Get all video files
        List<Path> files = tvMovieService.getMovieFiles(seasonDir);
        assertNotNull(files);
        assertEquals(1, files.size());

        // Refactor files
        List<String> removableFolders = tvMovieService.refactorFilesAddTitle(seriesName, files, parentDir.toString(), Optional.empty());
        assertTrue(removableFolders.isEmpty());
    }

    @Test
    void testRefactorFilesAddTitleSeason3() throws IOException {
        // Setup test data
        Path seasonDir = Files.createDirectories(parentDir.resolve("Season 3"));
        Path file = Files.createFile(seasonDir.resolve("S03E01.mkv"));

        // Get all video files
        List<Path> files = tvMovieService.getMovieFiles(seasonDir);
        assertNotNull(files);
        assertEquals(1, files.size());

        // Refactor files
        List<String> removableFolders = tvMovieService.refactorFilesAddTitle(seriesName, files, parentDir.toString(), Optional.empty());
        assertTrue(removableFolders.isEmpty());
    }

    @Test
    void testRefactorFilesAddTitleSeason4() throws IOException {
        // Setup test data
        Path seasonDir = Files.createDirectories(parentDir.resolve("Season 4"));
        Path file = Files.createFile(seasonDir.resolve("S04E01.mkv"));

        // Get all video files
        List<Path> files = tvMovieService.getMovieFiles(seasonDir);
        assertNotNull(files);
        assertEquals(1, files.size());

        // Refactor files
        List<String> removableFolders = tvMovieService.refactorFilesAddTitle(seriesName, files, parentDir.toString(), Optional.empty());
        assertTrue(removableFolders.isEmpty());
    }
}
