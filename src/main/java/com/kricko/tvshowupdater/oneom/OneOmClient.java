package com.kricko.tvshowupdater.oneom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kricko.tvshowupdater.configuration.Config;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.Objects;

public class OneOmClient {

    private static final OkHttpClient httpClient = new OkHttpClient().newBuilder().build();

    public static Serial getSeriesDetails(Config config, long oneomId) throws IOException {
        var url = String.format(config.getOneOmSeries(), oneomId);
        var request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .build();
        var response = httpClient.newCall(request).execute();
        var mapper = new ObjectMapper();

        var series = mapper.readValue(Objects.requireNonNull(response.body()).string(), Series.class);
        return series.getData().getSerial();
    }
}
