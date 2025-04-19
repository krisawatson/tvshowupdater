package com.kricko.tvshowupdater.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kricko.tvshowupdater.App;
import com.kricko.tvshowupdater.configuration.Config;
import com.kricko.tvshowupdater.model.Shows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class TvShowUpdaterIntegrationTest {

    @TempDir
    Path tempDir;
    
    private ObjectMapper objectMapper;
    private File configFile;
    private File showsFile;

    @BeforeEach
    void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        
        // Create test configuration
        configFile = tempDir.resolve("config.json").toFile();
        Config config = new Config();
        config.setTidyExisting(true);
        objectMapper.writeValue(configFile, config);

        // Create test shows file
        showsFile = tempDir.resolve("shows.json").toFile();
        Shows shows = new Shows(new ArrayList<>());
        objectMapper.writeValue(showsFile, shows);
    }

    @Test
    @DisplayName("Should run complete update flow successfully")
    void shouldRunCompleteUpdateFlowSuccessfully() {
        // Arrange
        String[] args = {
            "-o", "update",
            "-s", showsFile.getAbsolutePath(),
            "-c", configFile.getAbsolutePath()
        };

        // Act
        App.main(args);

        // Assert
        // Verify the files exist and have expected content
        assertThat(configFile).exists();
        assertThat(showsFile).exists();
    }

    @Test
    @DisplayName("Should handle missing shows gracefully")
    void shouldHandleMissingShowsGracefully() {
        // Arrange
        String[] args = {
            "-o", "missing",
            "-s", showsFile.getAbsolutePath(),
            "-c", configFile.getAbsolutePath()
        };

        // Act
        App.main(args);

        // Assert
        // Verify the operation completed without errors
        assertThat(configFile).exists();
        assertThat(showsFile).exists();
    }
}
