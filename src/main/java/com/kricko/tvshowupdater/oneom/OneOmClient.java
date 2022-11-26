package com.kricko.tvshowupdater.oneom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kricko.tvshowupdater.configuration.Config;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static java.lang.Thread.currentThread;

public class OneOmClient {

    private static final OkHttpClient httpClient = new OkHttpClient().newBuilder().build();

    public static Optional<Serial> getSeriesDetails(Config config, long oneomId) throws IOException {
        var url = String.format(config.getOneOmSeries(), oneomId);
        var request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .build();
        var response = httpClient.newCall(request).execute();

        try (var body = response.body()) {
            var mapper = new ObjectMapper();

            var series = mapper.readValue(Objects.requireNonNull(body).string(), Series.class);
            return Optional.of(series.getData().getSerial());
        } catch (IOException e) {
            System.out.println(currentThread().getName() + " - Failed getting the list of completed torrents " + e.getLocalizedMessage());
        }
        return Optional.empty();
    }
}
