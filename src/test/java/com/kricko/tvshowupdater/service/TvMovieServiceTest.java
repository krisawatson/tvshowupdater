package com.kricko.tvshowupdater.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.matchers.JUnitMatchers.containsString;

@Execution(ExecutionMode.CONCURRENT)
class TvMovieServiceTest {
    private static final String seriesName = "Test";
    private static final String parentDir = "TvShows/" + seriesName;

    @Test
    @DisplayName("Test testRefactorFilesAddTitleSeason3 with valid data")
    public void testRefactorFilesAddTitleSeason3() throws IOException, URISyntaxException {
        List<Path> files = Arrays.asList(buildPath(3, "S03E05.mkv"),
                                         buildPath(3, "S03E06.mkv"));
        Optional<String> seriesId = Optional.of("1234");

        List<String> removableFolders = TvMovieService.refactorFilesAddTitle(seriesName, files, parentDir, seriesId);

        assertTrue(removableFolders.isEmpty());
    }

    @Test
    @DisplayName("Test identifyPotentialMissingEpisodes Season 1 with valid data returns single episode")
    void testIdentifyPotentialMissingEpisodes() throws IOException, URISyntaxException {
        // Call the method under test
        List<String> result = TvMovieService.identifyPotentialMissingEpisodes(getTvShowDir(1), emptyList());

        // Assert the result (add your assertions here)
        assertEquals(1, result.size());
        assertThat(result.getFirst(), containsString("- Season 1, Episodes [3]")); // Example missing episode
    }



    @Test
    @DisplayName("Test identifyPotentialMissingEpisodes Season 2 with valid data returns multiple episodes")
    void testIdentifyPotentialMissingEpisodesMultipleEpisodes() throws IOException, URISyntaxException {
        // Call the method under test
        List<String> result = TvMovieService.identifyPotentialMissingEpisodes(getTvShowDir(2), emptyList());

        // Assert the result (add your assertions here)
        assertEquals(1, result.size());
        assertThat(result.getFirst(), containsString("- Season 2, Episodes [3, 4, 5]")); // Example missing episode
    }

    // Helper method to create a mock Path
    private Path buildPath(int season, String fileName) throws URISyntaxException {
        Path tvShowDir = getTvShowDir(season);
        return Paths.get(String.valueOf(tvShowDir), fileName);
    }

    private Path getTvShowDir(int season) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        return Paths.get(classLoader.getResource(parentDir + "/Season " + season).toURI());
    }
}