package net.lumae.database.logging;



import org.slf4j.event.Level;

import java.util.Arrays;
import java.util.HashMap;

public enum JavaLevel {
    ERROR(Level.ERROR),
    WARN(Level.WARN),
    INFO(Level.INFO),
    DEBUG(Level.DEBUG),
    TRACE(Level.TRACE);

    private Level slf4j;
    private HashMap<Level, JavaLevel> inverseMap = new HashMap<>();

    JavaLevel(Level slf4j) {
        this.slf4j = slf4j;
        Arrays.asList(values()).forEach(
                classic -> inverseMap.put(classic.getSlf4j(), classic)
        );
    }

    public static JavaLevel getBySlf4j(Level slf4j) {
        return DEBUG.inverseMap.getOrDefault(slf4j, ERROR);
    }

    public Level getSlf4j() {
        return slf4j;
    }



}
