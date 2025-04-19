package com.kricko.tvshowupdater.thread;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.service.TvMovieService;
import com.kricko.tvshowupdater.thetvdb.TheTVDBApi;
import com.kricko.tvshowupdater.utils.Constants;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class IdentifyPotentialMissingThread extends Thread {

    private final Details show;
    private final List<String> ignorable;
    private final TvMovieService tvMovieService;

    public IdentifyPotentialMissingThread(Details show, List<String> ignorable) {
        this.show = show;
        this.ignorable = ignorable;
        this.tvMovieService = new TvMovieService(new TheTVDBApi(Constants.API_KEY));
    }

    @Override
    public void run() {
        try {
            Path path = Path.of(show.getPath());
            List<String> missingSeasons = tvMovieService.identifyPotentialMissingSeasons(path);
            if (!missingSeasons.isEmpty()) {
                log.info("Missing season directories for {}: {}", show.getName(), missingSeasons);
            }

            List<Path> directories = tvMovieService.getDirectories(path);
            for (Path dir : directories) {
                List<String> missingEpisodes = tvMovieService.identifyPotentialMissingEpisodes(dir, ignorable);
                if (!missingEpisodes.isEmpty()) {
                    log.info("Missing episodes for {}: {}", show.getName(), missingEpisodes);
                }
            }
        } catch (IOException e) {
            log.error("Failed to identify missing episodes for {}", show.getName(), e);
        }
    }
}
