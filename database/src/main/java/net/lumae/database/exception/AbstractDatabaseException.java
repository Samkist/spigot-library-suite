package net.lumae.database.exception;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import net.lumae.database.logging.JavaLevel;
import net.lumae.database.logging.LogbackLevel;
import net.lumae.database.logging.LoggerLevel;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.function.BiConsumer;

public class AbstractDatabaseException extends Exception {

    private final BiConsumer<String, Logger> exceptionLog;
    private Logger logger;
    private org.slf4j.Logger slfLogger;
    private final String message;
    private final LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    public AbstractDatabaseException(String message, Logger logger) {
        super(message);
        this.message = message;
        this.logger = logger;
        exceptionLog = futureLog(logger.getLevel());
    }

    public AbstractDatabaseException(String message, Logger logger, Level level) {
        super(message);
        this.message = message;
        this.logger = logger;
        this.exceptionLog = futureLog(level);
    }

    public AbstractDatabaseException(String message, org.slf4j.Logger logger) {
        super(message);
        this.message = message;
        this.slfLogger = logger;
        this.exceptionLog = futureLog(LoggerLevel.getByLogger(logger));
    }

    public AbstractDatabaseException(String message, org.slf4j.Logger logger, Level level) {
        super(message);
        this.message = message;
        this.slfLogger = logger;
        this.exceptionLog = futureLog(level);
    }



    public void log() {
        exceptionLog.accept(message, logger);
    }

    protected BiConsumer<String, Logger> futureLog(LoggerLevel level) {
        return (msg, log) -> {
                switch(level) {
                    case ERROR -> log.error(msg);
                    case WARN -> log.warn(msg);
                    case INFO -> log.info(msg);
                    case DEBUG -> log.debug(msg);
                    case TRACE -> log.trace(msg);
                }};
    }

    protected BiConsumer<String, Logger> futureLog(JavaLevel level) {
        return futureLog(LoggerLevel.getLoggerLevel(level));
    }

    protected BiConsumer<String, Logger> futureLog(LogbackLevel level) {
        return futureLog(LoggerLevel.getLoggerLevel(level));
    }

    public BiConsumer<String, Logger> futureLog(Level java) {
        return futureLog(JavaLevel.getBySlf4j(java));
    }

    public BiConsumer<String, Logger> futureLog(ch.qos.logback.classic.Level logback) {
        return futureLog(LogbackLevel.getByLogback(logback));
    }




}
