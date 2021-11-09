package net.lumae.database;

import net.lumae.database.filter.Filter;

import java.util.Collection;
import java.util.Map;

public interface DatabaseController<T> {
    T save(Filter<T> filter, T save);

    Collection<T> save(Filter<T> filter, Collection<T> save);

    T queryField(Filter<T> filter);

    Collection<T> queryFields(Filter<T> filter);

    T highestField(Filter<T> filter);

    Collection<T> highestFields(Filter<T> filter);

    T lowestField(Filter<T> filter);

    Collection<T> lowestFields(Filter<T> filter);

    Boolean removeObject(Filter<T> filter);

    Boolean removeObjects(Filter<T> filter);



}
