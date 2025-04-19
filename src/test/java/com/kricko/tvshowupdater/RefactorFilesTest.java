package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Shows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class RefactorFilesTest {

    @TempDir
    Path tempDir;

    private Shows shows;
    private Details testShow;

    @BeforeEach
    void setUp() {
        testShow = Details.builder()
                .name("TestShow")
                .path(tempDir.toString().replaceAll("\\\\", "/"))
                .skipSeason(new ArrayList<>())
                .tvdbSeriesId(Optional.of("12345"))
                .build();
        shows = new Shows(List.of(testShow));
    }

    private void createTestFiles(String... filenames) throws IOException {
        for (String filename : filenames) {
            Path filePath = tempDir.resolve(filename);
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        }
    }

    @Test
    @DisplayName("Should organize files into season directories")
    void shouldOrganizeFilesIntoSeasonDirectories() throws IOException {
        // Arrange
        createTestFiles(
            "TestShow/TestShow.S01E01.mkv",
            "TestShow/TestShow.S01E02.mp4"
        );

        // Act
        RefactorFiles.tidyFolders(false, shows);

        // Assert
        Path seasonDir = tempDir.resolve("TestShow/Season 1");
        assertThat(seasonDir).exists();
        assertThat(seasonDir.resolve("TestShow.S01E01.mkv")).exists();
        assertThat(seasonDir.resolve("TestShow.S01E02.mp4")).exists();
    }

    @Test
    @DisplayName("Should handle multiple seasons")
    void shouldHandleMultipleSeasons() throws IOException {
        // Arrange
        createTestFiles(
            "TestShow/TestShow.S01E01.mkv",
            "TestShow/TestShow.S02E01.mp4"
        );

        // Act
        RefactorFiles.tidyFolders(false, shows);

        // Assert
        Path season1Dir = tempDir.resolve("TestShow/Season 1");
        Path season2Dir = tempDir.resolve("TestShow/Season 2");
        assertThat(season1Dir).exists();
        assertThat(season2Dir).exists();
        assertThat(season1Dir.resolve("TestShow.S01E01.mkv")).exists();
        assertThat(season2Dir.resolve("TestShow.S02E01.mp4")).exists();
    }

    @Test
    @DisplayName("Should skip non-video files")
    void shouldSkipNonVideoFiles() throws IOException {
        // Arrange
        createTestFiles(
            "TestShow/TestShow.S01E01.mkv",
            "TestShow/readme.txt"
        );

        // Act
        RefactorFiles.tidyFolders(false, shows);

        // Assert
        Path seasonDir = tempDir.resolve("TestShow/Season 1");
        assertThat(seasonDir).exists();
        assertThat(seasonDir.resolve("TestShow.S01E01.mkv")).exists();
        assertThat(tempDir.resolve("TestShow/readme.txt")).exists();
    }

    @Test
    @DisplayName("Should handle invalid show paths")
    void shouldHandleInvalidShowPaths() throws IOException {
        // Arrange
        Details invalidShow = Details.builder()
                .name("InvalidShow")
                .path("/invalid/path")
                .skipSeason(new ArrayList<>())
                .tvdbSeriesId(Optional.of("12345"))
                .build();
        shows = new Shows(List.of(invalidShow));

        // Act & Assert
        RefactorFiles.tidyFolders(false, shows);
        // No exception should be thrown
    }

    @Test
    @DisplayName("Should handle multiple shows")
    void shouldHandleMultipleShows() throws IOException {
        // Arrange
        Details show1 = Details.builder()
                .name("TestShow")
                .path(tempDir.toString().replaceAll("\\\\", "/"))
                .skipSeason(new ArrayList<>())
                .tvdbSeriesId(Optional.of("12345"))
                .build();
        Details show2 = Details.builder()
                .name("AnotherShow")
                .path(tempDir.toString().replaceAll("\\\\", "/"))
                .skipSeason(new ArrayList<>())
                .tvdbSeriesId(Optional.of("67890"))
                .build();
        shows = new Shows(List.of(show1, show2));

        createTestFiles(
            "TestShow/TestShow.S01E01.mkv",
            "AnotherShow/AnotherShow.S01E01.mp4"
        );

        // Act
        RefactorFiles.tidyFolders(false, shows);

        // Assert
        Path show1SeasonDir = tempDir.resolve("TestShow/Season 1");
        Path show2SeasonDir = tempDir.resolve("AnotherShow/Season 1");
        assertThat(show1SeasonDir).exists();
        assertThat(show2SeasonDir).exists();
        assertThat(show1SeasonDir.resolve("TestShow.S01E01.mkv")).exists();
        assertThat(show2SeasonDir.resolve("AnotherShow.S01E01.mp4")).exists();
    }
}
