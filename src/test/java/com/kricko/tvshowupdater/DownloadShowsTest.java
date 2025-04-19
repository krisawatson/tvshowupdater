package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.configuration.Config;
import com.kricko.tvshowupdater.configuration.TorrentConfig;
import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Shows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DownloadShowsTest {

    @Mock
    private Shows shows;

    private Config config;

    @BeforeEach
    void setUp() {
        config = new Config();
        TorrentConfig torrentConfig = new TorrentConfig();
        torrentConfig.setWebhost("localhost");
        torrentConfig.setWebport(8080);
        torrentConfig.setWebuser("admin");
        torrentConfig.setWebpass("adminadmin");
        config.setQBitTorrentConfig(torrentConfig);
    }

    @Test
    @DisplayName("Should attempt to download shows when valid configuration is provided")
    void shouldAttemptToDownloadShowsWhenValidConfigurationIsProvided() {
        // Arrange
        Details show1 = new Details();
        show1.setName("Show1");
        show1.setRegexName("S01E01");
        show1.setRssFeedId(Optional.of(1));
        
        Details show2 = new Details();
        show2.setName("Show2");
        show2.setRegexName("S02E03");
        show2.setRssFeedId(Optional.of(2));
        
        List<Details> showsList = Arrays.asList(show1, show2);
        when(shows.shows()).thenReturn(showsList);

        // Act
        DownloadShows.doDownload(config, shows);

        // Assert
        verify(shows, times(1)).shows();
        verifyNoMoreInteractions(shows);
    }

    @Test
    @DisplayName("Should handle empty shows list")
    void shouldHandleEmptyShowsList() {
        // Arrange
        when(shows.shows()).thenReturn(Collections.emptyList());

        // Act
        DownloadShows.doDownload(config, shows);

        // Assert
        verify(shows, times(1)).shows();
        verifyNoMoreInteractions(shows);
    }

    @Test
    @DisplayName("Should handle null shows list")
    void shouldHandleNullShowsList() {
        // Arrange
        when(shows.shows()).thenReturn(null);

        // Act
        DownloadShows.doDownload(config, shows);

        // Assert
        verify(shows, times(1)).shows();
        verifyNoMoreInteractions(shows);
    }

    @Test
    @DisplayName("Should handle exception during show processing")
    void shouldHandleExceptionDuringShowProcessing() {
        // Arrange
        Details show = new Details();
        show.setName("Show1");
        show.setRegexName("S01E01");
        show.setRssFeedId(Optional.of(1));
        List<Details> showsList = Collections.singletonList(show);
        when(shows.shows()).thenReturn(showsList);

        // Act & Assert
        assertDoesNotThrow(() -> DownloadShows.doDownload(config, shows));
        verify(shows, times(1)).shows();
        verifyNoMoreInteractions(shows);
    }

    @Test
    @DisplayName("Should validate qBittorrent configuration")
    void shouldValidateQBittorrentConfiguration() {
        // Arrange
        config.setQBitTorrentConfig(null);

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> DownloadShows.doDownload(config, shows));
        verifyNoInteractions(shows);
    }
}
