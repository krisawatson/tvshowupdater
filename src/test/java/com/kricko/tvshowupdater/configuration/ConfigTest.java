package com.kricko.tvshowupdater.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {
    
    private ObjectMapper objectMapper;
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Should load valid configuration file")
    void shouldLoadValidConfigurationFile() throws IOException {
        // Arrange
        File configFile = tempDir.resolve("config.json").toFile();
        Config expectedConfig = createSampleConfig();
        objectMapper.writeValue(configFile, expectedConfig);

        // Act
        Config actualConfig = objectMapper.readValue(configFile, Config.class);

        // Assert
        assertThat(actualConfig)
            .isNotNull()
            .satisfies(config -> {
                assertThat(config.isTidyExisting()).isEqualTo(expectedConfig.isTidyExisting());
                assertThat(config.getQBitTorrentConfig())
                    .isNotNull()
                    .satisfies(torrentConfig -> {
                        assertThat(torrentConfig.getWebhost()).isEqualTo(expectedConfig.getQBitTorrentConfig().getWebhost());
                        assertThat(torrentConfig.getWebport()).isEqualTo(expectedConfig.getQBitTorrentConfig().getWebport());
                    });
            });
    }

    @Test
    @DisplayName("Should throw exception when configuration file is invalid")
    void shouldThrowExceptionWhenConfigurationFileIsInvalid() {
        // Arrange
        File configFile = tempDir.resolve("invalid-config.json").toFile();

        // Act & Assert
        assertThatThrownBy(() -> objectMapper.readValue(configFile, Config.class))
            .isInstanceOf(IOException.class);
    }

    private Config createSampleConfig() {
        Config config = new Config();
        config.setTidyExisting(true);
        
        TorrentConfig torrentConfig = new TorrentConfig();
        torrentConfig.setWebhost("localhost");
        torrentConfig.setWebport(8080);
        config.setQBitTorrentConfig(torrentConfig);
        
        return config;
    }
}
