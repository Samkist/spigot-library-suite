package net.lumae.database.logging;

import ch.qos.logback.classic.Level;

import java.util.Arrays;
import java.util.HashMap;

public enum LogbackLevel {

    ERROR(Level.ERROR),
    WARN(Level.WARN),
    INFO(Level.INFO),
    DEBUG(Level.DEBUG),
    TRACE(Level.TRACE);

    private Level logback;
    private HashMap<Level, LogbackLevel> inverseMap = new HashMap<>();

    LogbackLevel(Level logback) {
        this.logback = logback;
        Arrays.asList(values()).forEach(
                classic -> inverseMap.put(classic.getLogback(), classic)
        );
    }


    public static LogbackLevel getByLogback(Level logback) {
        return DEBUG.inverseMap.getOrDefault(logback, ERROR);
    }

    public Level getLogback() {
        return logback;
    }
}
