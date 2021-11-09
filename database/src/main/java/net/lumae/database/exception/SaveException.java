package net.lumae.database.exception;

import ch.qos.logback.classic.Logger;
import org.slf4j.event.Level;

public class SaveException extends AbstractDatabaseException {


    public SaveException(String message, Logger logger) {
        super(message, logger);
    }

    public SaveException(String message, Logger logger, Level level) {
        super(message, logger, level);
    }

    public SaveException(String message, org.slf4j.Logger logger) {
        super(message, logger);
    }

    public SaveException(String message, org.slf4j.Logger logger, Level level) {
        super(message, logger, level);
    }
}
