package com.kricko.tvshowupdater.torrent;

import com.kricko.tvshowupdater.configuration.TorrentConfig;
import com.kricko.tvshowupdater.model.Torrent;
import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QBitTorrentTest {

    @Mock
    private OkHttpClient httpClient;

    @Mock
    private Call call;

    @Mock
    private Response response;

    @Mock
    private ResponseBody responseBody;

    private QBitTorrent qBitTorrent;
    private TorrentConfig config;

    @BeforeEach
    void setUp() {
        config = new TorrentConfig();
        config.setWebhost("localhost");
        config.setWebport(8080);
        config.setWebuser("admin");
        config.setWebpass("password");
        
        // Create a new instance of QBitTorrent with our mocked httpClient
        qBitTorrent = new QBitTorrent(config) {
            @Override
            protected OkHttpClient getHttpClient() {
                return httpClient;
            }
        };
    }

    private void setupCommonMocks() throws IOException {
        when(httpClient.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(response.body()).thenReturn(responseBody);
        when(response.headers()).thenReturn(Headers.of("Set-Cookie", "test-cookie"));
    }

    @Test
    @DisplayName("Should successfully get list of torrents")
    void shouldGetListOfTorrentsSuccessfully() throws IOException, InterruptedException {
        // Arrange
        setupCommonMocks();
        when(responseBody.string()).thenReturn("[]");

        // Act
        List<Torrent> torrents = qBitTorrent.getListOfTorrents(Filter.ALL);

        // Assert
        assertThat(torrents).isEmpty();
        verify(httpClient, times(2)).newCall(any(Request.class)); // One for getToken, one for getListOfTorrents
        verify(responseBody, times(2)).close(); // One for getToken, one for getListOfTorrents
    }

    @Test
    @DisplayName("Should add new torrent successfully")
    void shouldAddNewTorrentSuccessfully() throws IOException {
        // Arrange
        String magnetLink = "magnet:?xt=test";
        Path downloadPath = Paths.get("/downloads");
        setupCommonMocks();

        // Act
        qBitTorrent.addNewTorrent(magnetLink, downloadPath);

        // Assert
        verify(httpClient, times(2)).newCall(any(Request.class)); // One for getToken, one for addNewTorrent
        verify(responseBody, times(2)).close(); // One for getToken, one for addNewTorrent
    }

    @Test
    @DisplayName("Should remove completed torrents")
    void shouldRemoveCompletedTorrents() throws IOException {
        // Arrange
        String hashes = "hash1,hash2";
        setupCommonMocks();

        // Act
        qBitTorrent.removeCompletedTorrents(hashes);

        // Assert
        verify(httpClient, times(2)).newCall(any(Request.class)); // One for getToken, one for removeCompletedTorrents
        verify(responseBody, times(2)).close(); // One for getToken, one for removeCompletedTorrents
    }
}
