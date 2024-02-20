package com.kricko.tvshowupdater.service;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

class SeasonDirectoryVisitor extends SimpleFileVisitor<Path> {
    private static final String SEASON_PREFIX = "Season";
    private final List<Path> seasonDirs = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (Files.isDirectory(file) && isSeasonDirectory(file)) {
            seasonDirs.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        // Handle the failure if needed
        return FileVisitResult.CONTINUE;
    }

    List<Path> getSeasonDirs() {
        return seasonDirs;
    }

    private boolean isSeasonDirectory(Path directory) {
        String directoryName = directory.getFileName().toString();
        return directoryName.startsWith(SEASON_PREFIX);
    }
}
