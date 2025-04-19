package com.kricko.tvshowupdater.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TvShowUtilsTest {

    @Test
    @DisplayName("Should extract season and episode numbers from SxxExx format")
    void shouldExtractSeasonAndEpisodeFromSxxExxFormat() {
        // Arrange
        String title = "Show.Name.S01E02.Episode.Title.mp4";

        // Act
        int[] result = TvShowUtils.getSeasonAndEpisode(title);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result[0]).isEqualTo(1); // Season
        assertThat(result[1]).isEqualTo(2); // Episode
    }

    @Test
    @DisplayName("Should extract season and episode numbers from xxXxx format")
    void shouldExtractSeasonAndEpisodeFromXxxFormat() {
        // Arrange
        String title = "Show.Name.1x02.Episode.Title.mp4";

        // Act
        int[] result = TvShowUtils.getSeasonAndEpisode(title);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result[0]).isEqualTo(1); // Season
        assertThat(result[1]).isEqualTo(2); // Episode
    }

    @Test
    @DisplayName("Should return null for invalid format")
    void shouldReturnNullForInvalidFormat() {
        // Arrange
        String title = "Show.Name.102.Episode.Title.mp4";

        // Act
        int[] result = TvShowUtils.getSeasonAndEpisode(title);

        // Assert
        assertThat(result).isNull();
    }
}
