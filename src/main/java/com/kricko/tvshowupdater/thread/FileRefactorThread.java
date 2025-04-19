package com.kricko.tvshowupdater.thread;

import com.kricko.tvshowupdater.service.TvMovieService;
import com.kricko.tvshowupdater.thetvdb.TheTVDBApi;
import com.kricko.tvshowupdater.utils.Constants;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class FileRefactorThread extends Thread {

    private final String name;
    private final String path;
    private final List<Integer> skip;
    private final Optional<String> seriesId;
    private final TvMovieService tvMovieService;

    public FileRefactorThread(String name, String path, List<Integer> skip, Optional<String> seriesId) {
        this.name = name;
        this.path = path;
        this.skip = skip;
        this.seriesId = seriesId;
        this.tvMovieService = new TvMovieService(new TheTVDBApi(Constants.API_KEY));
    }

    @Override
    public void run() {
        try {
            Path showPath = Path.of(path);

            // Get all video files from the base path
            List<Path> allFiles = new ArrayList<>();
            if (Files.exists(showPath)) {
                allFiles = tvMovieService.getMovieFiles(showPath);
            }

            // Process each file
            if (!allFiles.isEmpty()) {
                for (Path file : allFiles) {
                    String fileName = file.getFileName().toString();
                    if (!skip.contains(Integer.parseInt(fileName.replaceAll(".*S(\\d+)E\\d+.*", "$1")))) {
                        int season = Integer.parseInt(fileName.replaceAll(".*S(\\d+)E\\d+.*", "$1"));
                        // Create season directory
                        Path seasonDir = showPath.resolve("Season " + season);
                        Files.createDirectories(seasonDir);

                        // Move the file to the season directory
                        Path targetFile = seasonDir.resolve(fileName);
                        if (!Files.exists(targetFile)) {
                            Files.move(file, targetFile);
                        }

                        // Refactor the file names
                        List<String> dirsToRemove = tvMovieService.refactorFilesAddTitle(name, List.of(targetFile), path, seriesId);
                        for (String remove : dirsToRemove) {
                            log.info("About to delete dir {}", remove);
                            tvMovieService.deleteDirectory(showPath, remove);
                        }
                    }
                }
            }
        } catch (IOException e) {
            log.error("Failed to process show {}", name, e);
        }
    }
}
