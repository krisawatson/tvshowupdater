package com.kricko.tvshowupdater;

import com.kricko.tvshowupdater.model.Details;
import com.kricko.tvshowupdater.model.Shows;
import com.kricko.tvshowupdater.thread.FileRefactorThread;
import com.kricko.tvshowupdater.utils.TvShowUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RefactorFiles {

    private RefactorFiles() {
        // Hide constructor
    }

    public static void tidyFolders(boolean existing, Shows shows) {
        Set<Details> details = existing ? new HashSet<>(shows.shows()) : getDetailsForTidyUp(shows);
        if (!details.isEmpty()) {
            // Create a CompletableFuture for each show
            CompletableFuture<?>[] futures = details.stream()
                .map(show -> CompletableFuture.runAsync(new FileRefactorThread(
                    show.getName(),
                    show.getPath(),
                    show.getSkipSeason(),
                    show.getTvdbSeriesId()
                )))
                .toArray(CompletableFuture[]::new);

            // Wait for all futures to complete
            CompletableFuture.allOf(futures).join();
        }
    }

    private static Set<Details> getDetailsForTidyUp(Shows shows) {
        List<String> directories = TvShowUtils.getListOfTidyUpDirs();
        Set<Details> details = new HashSet<>();

        if (!directories.isEmpty()) {
            for (String dir : directories) {
                details.addAll(shows.shows()
                                    .parallelStream()
                                    .filter(showDetails -> dir.replaceAll("\\\\", "/").startsWith(showDetails.getPath()))
                                    .toList());
            }
        }

        return details;
    }
}
