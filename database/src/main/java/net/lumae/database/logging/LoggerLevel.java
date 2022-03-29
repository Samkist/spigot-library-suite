package net.lumae.database.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;

public enum LoggerLevel {

    ERROR(JavaLevel.ERROR, LogbackLevel.ERROR),
    WARN(JavaLevel.WARN, LogbackLevel.WARN),
    INFO(JavaLevel.INFO, LogbackLevel.INFO),
    DEBUG(JavaLevel.DEBUG, LogbackLevel.DEBUG),
    TRACE(JavaLevel.TRACE, LogbackLevel.TRACE);

    private JavaLevel javaLevel;
    private LogbackLevel classic;
    private HashMap<JavaLevel, LogbackLevel> javaLogbackMap = new HashMap<>();
    private HashMap<LogbackLevel, JavaLevel> logbackJavaMap = new HashMap<>();

    LoggerLevel(JavaLevel javaLevel, LogbackLevel classic) {
        this.javaLevel = javaLevel;
        this.classic = classic;
        Arrays.asList(values()).forEach(l -> {
            javaLogbackMap.put(l.key(), l.value());
            logbackJavaMap.put(l.value(), l.key());
        });
    }

    public static LoggerLevel getLoggerLevel(JavaLevel level) {
        return switch(level) {
            case ERROR -> ERROR;
            case WARN -> WARN;
            case INFO -> INFO;
            case DEBUG -> DEBUG;
            case TRACE -> TRACE;
        };
    }

    public static LoggerLevel getLoggerLevel(LogbackLevel level) {
        return switch(level) {
            case ERROR -> ERROR;
            case WARN -> WARN;
            case INFO -> INFO;
            case DEBUG -> DEBUG;
            case TRACE -> TRACE;
        };
    }

    public static LoggerLevel getLoggerLevel(org.slf4j.event.Level level) {
        return getLoggerLevel(JavaLevel.getBySlf4j(level));
    }

    public static LoggerLevel getLoggerLevel(Level level) {
        return getLoggerLevel(LogbackLevel.getByLogback(level));
    }

    public static LoggerLevel getByLogger(org.slf4j.Logger logger) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger(logger.getName());
        return getLoggerLevel(rootLogger.getLevel());
    }

    public JavaLevel getJavaLevel() {
        return javaLevel;
    }

    public static JavaLevel getJavaLevel(LogbackLevel level) {
        return DEBUG.logbackJavaMap.getOrDefault(
                level, JavaLevel.ERROR
        );
    }

    public static org.slf4j.event.Level getJavaLevel(Level level) {
        return DEBUG.logbackJavaMap.getOrDefault(
                LogbackLevel.getByLogback(level), JavaLevel.ERROR
        ).getSlf4j();
    }

    private JavaLevel key() {
        return getJavaLevel();
    }

    public LogbackLevel getLogbackLevel() {
        return classic;
    }

    public static LogbackLevel getLogbackLevel(JavaLevel level) {
        return DEBUG.javaLogbackMap.getOrDefault(
                level, LogbackLevel.ERROR
        );
    }

    public static Level getLogbackLevel(org.slf4j.event.Level java) {
        return DEBUG.javaLogbackMap.getOrDefault(
                JavaLevel.getBySlf4j(java), LogbackLevel.ERROR
        ).getLogback();
    }

    private LogbackLevel value() {
        return getLogbackLevel();
    }
}
