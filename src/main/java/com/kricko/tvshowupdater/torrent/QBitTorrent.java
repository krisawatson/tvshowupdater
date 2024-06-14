package com.kricko.tvshowupdater.torrent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kricko.tvshowupdater.configuration.TorrentConfig;
import com.kricko.tvshowupdater.model.Torrent;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 */
@Slf4j
public class QBitTorrent {

	private final TorrentConfig torrentConfig;
	private static List<String> cookieList;
	private static final OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
	private static final String NOT_RUNNING_FAILURE = "Failed to connect to";
	private static final int WAIT_TIME = 5000;

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
					.addHeader("Cookie", cookieList.getFirst())
					.build();

			Response response = httpClient.newCall(request).execute();
		} catch (IOException | InterruptedException e) {
			log.error("Failed during getting the list of torrents", e);
		}
    }

	/**
	 * Method getListOfTorrents.
	 */
	public List<Torrent> getListOfTorrents(Filter filter) throws IOException, InterruptedException {
		String url = String.format("http://%s:%d/api/v2/torrents/info?filter=%s",
								   torrentConfig.getWebhost(), torrentConfig.getWebport(), filter.getFilterName());
		getTokenWithRetry();
		Request request = new Request.Builder()
				.url(url)
				.method("GET", null)
				.addHeader("Cookie", cookieList.getFirst())
				.build();
		try(Response response = httpClient.newCall(request).execute(); ResponseBody body = response.body()) {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.readValue(Objects.requireNonNull(body).string(),
										new TypeReference<>() {
										});
		} catch (SocketTimeoutException e) {
			log.info("Socket Timed out, continuing");
		} catch (IOException e) {
			log.error("Failed getting the list of completed torrents", e);
		}
		return Collections.emptyList();
	}

	public void removeCompletedTorrents(String hashes) {
		String url = String.format("http://%s:%d/api/v2/torrents/delete",
								   torrentConfig.getWebhost(), torrentConfig.getWebport());

		try {
			getTokenWithRetry();
			RequestBody requestBody = new FormBody.Builder().add("hashes", hashes)
															.add("deleteFiles", "false")
															.build();
			Request request = new Request.Builder()
					.url(url)
					.method("POST", requestBody)
					.addHeader("Cookie", cookieList.getFirst())
					.build();

			httpClient.newCall(request).execute();
		} catch (IOException | InterruptedException e) {
			log.error("Failed during removed completed torrents", e);
		}
	}

	/**
	 * Method getToken.
     */
	private void getToken() throws IOException{
		Request request = new Request.Builder().url(String.format("http://%s:%d/api/v2/auth/login",
																  torrentConfig.getWebhost(),
																  torrentConfig.getWebport()))
											   .method("GET", null)
											   .build();
		Response response = httpClient.newCall(request).execute();

		cookieList = response.headers().values("Set-Cookie");
	}

	private void getTokenWithRetry() throws IOException, InterruptedException {
		try {
			getToken();
		} catch (IOException e) {
			if (e.getLocalizedMessage().startsWith(NOT_RUNNING_FAILURE)) {
				new ProcessBuilder().command(torrentConfig.getClient()).start();
				Thread.sleep(WAIT_TIME);
				getToken();
			}
		}
	}
}
