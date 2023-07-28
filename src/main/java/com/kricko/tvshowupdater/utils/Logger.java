package com.kricko.tvshowupdater.utils;

public class Logger {
    public static void logLine(String messageFormat, String className) {
        System.out.printf("[%s] " + messageFormat + "%n", className);
    }

    public static void logError(String messageFormat, String className) {
        System.err.printf("[%s] " + messageFormat + "%n", className);
    }
}
