package com.kricko.tvshowupdater.torrent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kricko.tvshowupdater.configuration.TorrentConfig;
import com.kricko.tvshowupdater.model.Torrent;
import okhttp3.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.lang.Thread.currentThread;

/**
 */
public class QBitTorrent {

	private final TorrentConfig torrentConfig;
	private static List<String> cookieList;
	private static final OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
	private static final String NOT_RUNNING_FAILURE = "Failed to connect to";

	public QBitTorrent(TorrentConfig torrentConfig){
		this.torrentConfig = torrentConfig;
	}

	public void addNewTorrent(String link, Path dir) {
		String url = String.format("http://%s:%d/api/v2/torrents/add",
								   torrentConfig.getWebhost(), torrentConfig.getWebport());

		try {
			getTokenWithRetry();
			RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
														  .addFormDataPart("urls", link)
														  .addFormDataPart("savepath", dir.toString())
														  .build();
			Request request = new Request.Builder()
					.url(url)
					.method("POST", body)
					.addHeader("Cookie", cookieList.get(0))
					.build();

			Response response = httpClient.newCall(request).execute();

			System.out.println("Response status to adding torrent was: " + response.code());
		} catch (IOException e) {
			System.out.println(currentThread().getName() + " - Failed during getting the list of torrents " + e.getLocalizedMessage());
		}
	}

	/**
	 * Method getListOfTorrents.
	 */
	public List<Torrent> getListOfTorrents(Filter filter) throws IOException {
		String url = String.format("http://%s:%d/api/v2/torrents/info?filter=%s",
								   torrentConfig.getWebhost(), torrentConfig.getWebport(), filter.getFilterName());
		try {
			getTokenWithRetry();
			Request request = new Request.Builder()
					.url(url)
					.method("GET", null)
					.addHeader("Cookie", cookieList.get(0))
					.build();
			Response response = httpClient.newCall(request).execute();

			ObjectMapper mapper = new ObjectMapper();
			var body = response.body();
			if (null != response.body()) {
				response.body().close();
			}

			return mapper.readValue(Objects.requireNonNull(body).string(),
					new TypeReference<>() {
					});
		} catch (IOException e) {
			System.out.println(currentThread().getName() + " - Failed getting the list of completed torrents " + e.getLocalizedMessage());
		}
		return Collections.emptyList();
	}

	public void removeCompletedTorrents(String hash) {
		String url = String.format("http://%s:%d/api/v2/torrents/delete?hashes=%s&deleteFiles=false",
								   torrentConfig.getWebhost(), torrentConfig.getWebport(), hash);

		try {
			getTokenWithRetry();
			Request request = new Request.Builder()
					.url(url)
					.method("GET", null)
					.addHeader("Cookie", cookieList.get(0))
					.build();

			httpClient.newCall(request).execute();
		} catch (IOException e) {
			System.err.println("Failed during removed completed torrents " + e.getLocalizedMessage());
		}
	}

	/**
	 * Method getToken.
	 * @throws IOException
	 */
	private void getToken() throws IOException{
		try {
			Request request = new Request.Builder()
					.url(String.format("http://%s:%d/api/v2/auth/login",
									   torrentConfig.getWebhost(), torrentConfig.getWebport()))
					.method("GET", null)
					.build();
			Response response = httpClient.newCall(request).execute();

			cookieList = response.headers().values("Set-Cookie");
		} catch (IOException e) {
			System.err.println("Failed during get token " + e.getLocalizedMessage());
		}
	}

	private void getTokenWithRetry() throws IOException {
		try {
			getToken();
		} catch (IOException e) {
			if (e.getLocalizedMessage().startsWith(NOT_RUNNING_FAILURE)) {
				Runtime.getRuntime().exec(torrentConfig.getClient());
				getToken();
			}
		}
	}
}
