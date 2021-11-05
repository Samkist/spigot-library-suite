package net.lumae.core.data.entities;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public interface DatabaseObject {
    String getCollectionName();

    static DatabaseObject anInstance() {
        List<Constructor<?>> constructors = Arrays.asList(DatabaseObject.class.getConstructors());
        Constructor<?> filtered = constructors.stream().filter(c ->
                c.getParameterCount() == 0)
                .findFirst()
                .orElse(null);
        @Nullable
        Object obj;
        try {
           obj  = filtered.newInstance();
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException foobar) {
            obj = null;
        }
        return obj instanceof DatabaseObject ? (DatabaseObject) obj : null;
    }
}
