package com.kricko.tvshowupdater.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TvMovieServiceTest {
    private static final String seriesName = "Family Guy";
    private static final String parentDir = "C:/Tv Shows/" + seriesName;
    private static final String seasonDir = "/Season 1";
    private static final String tvShowDir = parentDir + seasonDir;
    @InjectMocks
    private TvMovieService tvMovieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRefactorFilesAddTitle_Positive() throws IOException {
        List<Path> files = Arrays.asList(Paths.get(tvShowDir + "/file1.mkv"),
                                         Paths.get(tvShowDir + "/file2.mkv"));
        Optional<String> seriesId = Optional.of("1234");

        List<String> removableFolders = tvMovieService.refactorFilesAddTitle(seriesName, files, parentDir, seriesId);

        assertTrue(removableFolders.isEmpty());
    }
}