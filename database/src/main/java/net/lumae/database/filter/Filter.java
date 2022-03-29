package net.lumae.database.filter;

import com.mongodb.lang.Nullable;
import net.lumae.core.data.entities.LumaePlayer;
import net.lumae.database.MongoController;
import org.bson.conversions.Bson;
import java.util.Objects;

import static com.mongodb.client.model.Filters.*;

public abstract class Filter<T>  {

    private T object;
    private Integer count;
    private String key;
    private String stringValue;
    private Number numberValue;
    private Order order;
    private Integer bound;
    private Boolean isObjectFilter;
    private Boolean isNumber;
    private Boolean isLong;
    private Boolean isDouble;
    private Boolean isOrdered;
    private Boolean isBounded;


    protected Filter(@Nullable T object, Integer count, String key, String stringValue, Number numberValue, Order order, Integer bound) {
        this.object = object;
        this.count = count;
        this.key = key;
        this.stringValue = stringValue;
        this.numberValue = evaluateNumberValue(numberValue);
        this.order = order;
        this.bound = bound;


        this.isObjectFilter = Objects.nonNull(object);
        this.isOrdered = order != Order.NEITHER;
        this.isBounded = Objects.nonNull(bound) && bound <= 0;

    }

    protected Filter() {

    }

    private Number evaluateNumberValue(Number value) {
        if(Objects.isNull(value)) {
            isNumber = false;
            return null;
        } else {
            isNumber = true;
        }
        this.isDouble = value instanceof Double;

        if(!isDouble || !(value instanceof Long)) {
            this.isLong = true;
            return value.longValue();
        } else {
            this.isLong = false;
        }
        return value;
    }

    public T getObject() {
        return object;
    }

    public Integer getCount() {
        return count;
    }

    public String getKey() {
        return key;
    }

    public String getStringValue() {
        return stringValue;
    }

    public Double getDoubleValue() {
        return numberValue.doubleValue();
    }

    public Long getLongValue() {
        return numberValue.longValue();
    }

    public Order getOrder() {
        return order;
    }

    public Integer getBound() {
        return bound;
    }


    /*
    private Boolean isObjectFilter;
    private Boolean isNumber;
    private Boolean isLong;
    private Boolean isDouble;
    private Boolean isOrdered;
    private Boolean isBounded;
    private Boolean isSorted;
     */
    public Boolean isObjectFilter() {
        return isObjectFilter;
    }

    public Boolean isNumber() {
        return isNumber;
    }

    public Boolean isLong() {
        return isLong;
    }

    public Boolean isDouble() {
        return isDouble;
    }

    public Boolean isOrdered() {
        return isOrdered;
    }

    public Boolean isBounded() {
        return isBounded;
    }


    public static void main(String[] args) {
        Filter<LumaePlayer> filter = new FilterBuilder<LumaePlayer>().setFilter("lastName", "Samkist").setObject(new LumaePlayer()).build();
        MongoController<LumaePlayer> database = new MongoController<>(null);
        database.removeObject(filter);
    }

}
