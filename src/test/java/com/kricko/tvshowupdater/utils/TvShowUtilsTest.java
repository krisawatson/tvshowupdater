package com.kricko.tvshowupdater.utils;

import com.kricko.tvshowupdater.model.Episode;
import com.kricko.tvshowupdater.model.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

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

    @Test
    @DisplayName("Should build filename with proper format")
    void shouldBuildFilenameWithProperFormat() {
        // Arrange
        Episode episode = new Episode();
        episode.setSeasonNumber(1);
        episode.setEpisodeNumber(2);
        episode.setEpisodeName("Test Episode");

        // Act
        String result = TvShowUtils.buildFileName(episode);

        // Assert
        assertThat(result).isEqualTo("S01E02 - Test Episode");
    }

    @Test
    @DisplayName("Should build filename with special characters removed")
    void shouldBuildFilenameWithSpecialCharsRemoved() {
        // Arrange
        Episode episode = new Episode();
        episode.setSeasonNumber(1);
        episode.setEpisodeNumber(2);
        episode.setEpisodeName("Test: Episode? With/Special\\Chars");

        // Act
        String result = TvShowUtils.buildFileName(episode);

        // Assert
        assertThat(result).isEqualTo("S01E02 - Test Episode With Special Chars");
    }

    @Test
    @DisplayName("Should remove duplicate episodes")
    void shouldRemoveDuplicateEpisodes() {
        // Arrange
        Item item1 = new Item();
        item1.setShowId(1);
        item1.setTitle("Show.S01E01.HDTV");

        Item item2 = new Item();
        item2.setShowId(1);
        item2.setTitle("Show.S01E01.1080p");

        Item item3 = new Item();
        item3.setShowId(1);
        item3.setTitle("Show.S01E02.HDTV");

        List<Item> items = Arrays.asList(item1, item2, item3);
        String regex = "S([0-9]{2}E[0-9]{2})";

        // Act
        List<Item> result = TvShowUtils.removeDuplicateEpisodes(items, regex);

        // Assert
        assertThat(result).hasSize(2)
            .extracting(Item::getTitle)
            .contains("Show.S01E01.HDTV", "Show.S01E02.HDTV");
    }

    @Test
    @DisplayName("Should handle different shows with same episode numbers")
    void shouldHandleDifferentShowsWithSameEpisodes() {
        // Arrange
        Item item1 = new Item();
        item1.setShowId(1);
        item1.setTitle("Show1.S01E01.HDTV");

        Item item2 = new Item();
        item2.setShowId(2);
        item2.setTitle("Show2.S01E01.HDTV");

        List<Item> items = Arrays.asList(item1, item2);
        String regex = "S([0-9]{2}E[0-9]{2})";

        // Act
        List<Item> result = TvShowUtils.removeDuplicateEpisodes(items, regex);

        // Assert
        assertThat(result).hasSize(2)
            .extracting(Item::getTitle)
            .containsExactly("Show1.S01E01.HDTV", "Show2.S01E01.HDTV");
    }
}
