package net.lumae.database.filter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class FilterBuilder<T> {

    private Class cast;
    private T object;
    private Integer count = 0;
    private String key;
    private String stringValue;
    private Number numberValue;
    private Order order;
    private Integer bound;

    public FilterBuilder() {

    }

    public Filter<T> build() {
        //return new Filter<>(object, count, key, stringValue, numberValue, order, bound);
        List<Object> params = List.of(
                object, count, key, stringValue, numberValue, order, bound
        );
        try {
            if(MongoFilter.getFilterClass().isAssignableFrom(cast)) {
                return (MongoFilter<T>) MongoFilter.getFilterClass().getConstructors()[0].newInstance(params);
            } else {
                throw new Exception();
            }
        } catch (Exception foo) {
            return null;
        }
    }

    public FilterBuilder<T> setType(Class cast) {
        this.cast = cast;
        return this;
    }

    public FilterBuilder<T> setObject(T object) {
        return this;
    }

    public FilterBuilder<T> setCount(Integer count) {
        return this;
    }

    public FilterBuilder<T> setFilter(String key) {
        return this;
    }

    public FilterBuilder<T> setFilter(String key, String value) {
        return this;
    }

    public FilterBuilder<T> setFilter(String key, Number value) {
        return this;
    }

    public FilterBuilder<T> setOrder(Order order) {
        return this;
    }

    public FilterBuilder<T> setBound(Integer bound) {
        return this;
    }
}
