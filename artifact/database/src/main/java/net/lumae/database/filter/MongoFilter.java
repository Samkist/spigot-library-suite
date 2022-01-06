package net.lumae.database.filter;

import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;

import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

public class MongoFilter<T> extends Filter<T> {

    protected MongoFilter(T object, Integer count, String key, String stringValue, Number numberValue, Order order, Integer bound) {
        super(object, count, key, stringValue, numberValue, order, bound);
    }

    protected MongoFilter() {
        super();
    }

    public static Class<MongoFilter> getFilterClass() {
        return new MongoFilter().getFilterClass();
    }

    public Bson getFilter() {
        if(isObjectFilter()) {
            if(Objects.nonNull(getKey())) {
                return eq(getKey(), getObject());
            } else {
                return eq(getObject());
            }
        } else {
            if(isNumber()) {
                if(isDouble()) {
                    return eq(getKey(), getDoubleValue());
                } else {
                    return eq(getKey(), getLongValue());
                }
            } else {
                return eq(getKey(), getStringValue());
            }
        }
    }

    public Bson getSort() {
        return switch(getOrder()) {
            case ASCENDING -> Sorts.ascending(getKey());
            case DESCENDING -> Sorts.descending(getKey());
            case NEITHER -> null;
        };
    }
}
