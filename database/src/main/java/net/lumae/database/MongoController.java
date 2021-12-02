package net.lumae.database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.Sorts;
import net.lumae.database.exception.ExceptionPipeline;
import net.lumae.database.filter.Filter;
import net.lumae.database.filter.MongoFilter;
import net.lumae.database.wrapper.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MongoController<T> implements DatabaseController<T> {

    private static final ReplaceOptions replaceOptions = (new ReplaceOptions()).upsert(true);
    private final MongoCollection<T> collection;
    private final Logger logger = LoggerFactory.getLogger(MongoController.class);
    private final ExceptionPipeline exceptionPipeline = new ExceptionPipeline();


    public MongoController(MongoCollection<T> collection) {
        this.collection = collection;
    }

    @Override
    public T save(Filter<T> filter, T save) {
        if (!isMongoFilter(filter)) {
            return null;
        }
        MongoFilter<T> mFilter = (MongoFilter<T>) filter;
        Function<MongoCollection<T>, T> function = (coll -> {
            coll.replaceOne(
                    mFilter.getFilter(),
                    save,
                    replaceOptions
            );
            return save;
        });
        DatabaseEnqueue.enqueueOperation(new DatabaseOperation(function, collection));
        return save;
    }

    @Override
    public Collection<T> save(Filter<T> filter, Collection<T> save) {
        if (!isMongoFilter(filter)) {
            return null;
        }
        MongoFilter<T> mFilter = (MongoFilter<T>) filter;
        Function<MongoCollection<T>, Collection<T>> function = (coll -> {
            coll.bulkWrite(
                    save.stream().map(t ->
                            new ReplaceOneModel<>(
                                    mFilter.getFilter(),
                                    t,
                                    replaceOptions
                            )
                    ).collect(Collectors.toList())
            );
            return save;
        });
        DatabaseEnqueue.enqueueOperation(new DatabaseOperation(function, collection));
        return save;
    }

    @Override
    public T queryField(Filter<T> filter) {
        if (!isMongoFilter(filter)) {
            return null;
        }
        MongoFilter<T> mFilter = (MongoFilter<T>) filter;
        Function<MongoCollection<T>, T> function = (coll ->
                coll.find(
                        mFilter.getFilter()
                ).first()
        );

        return function.apply(collection);
    }

    @Override
    public Collection<T> queryFields(Filter<T> filter) {
        if (!isMongoFilter(filter)) {
            return null;
        }
        MongoFilter<T> mFilter = (MongoFilter<T>) filter;
        Function<MongoCollection<T>, Collection<T>> function = (coll -> {
            ArrayList<T> results = new ArrayList<>();
            FindIterable<T> iterable = coll.find(
                    mFilter.getFilter()
            );
            if (mFilter.isOrdered()) {
                iterable.sort(mFilter.getSort());
            }
            iterable.forEach(results::add);
            return results;
        });

        return function.apply(collection);

    }

    @Override
    public T highestField(Filter<T> filter) {
        if (!isMongoFilter(filter)) {
            return null;
        }
        MongoFilter<T> mFilter = (MongoFilter<T>) filter;
        Function<MongoCollection<T>, T> function = (coll ->
                coll.find(
                        mFilter.getFilter()
                ).sort(Sorts.descending(filter.getKey())).first()
        );

        return function.apply(collection);

    }

    @Override
    public Collection<T> highestFields(Filter<T> filter) {
        if (!isMongoFilter(filter)) {
            return null;
        }
        MongoFilter<T> mFilter = (MongoFilter<T>) filter;
        Function<MongoCollection<T>, Collection<T>> function = (coll -> {
            ArrayList<T> results = new ArrayList<>();
            coll.find(

                    ).sort(Sorts.descending(mFilter.getKey()))
                    .forEach(results::add);
            return results;
        });

        return function.apply(collection);
    }

    @Override
    public T lowestField(Filter<T> filter) {
        if (!isMongoFilter(filter)) {
            return null;
        }
        MongoFilter<T> mFilter = (MongoFilter<T>) filter;
        Function<MongoCollection<T>, T> function = (coll ->
                    coll.find(
                            mFilter.getFilter()
                    ).sort(
                            Sorts.ascending(mFilter.getKey())
                    ).first()
                );

        return function.apply(collection);
    }

    @Override
    public Collection<T> lowestFields(Filter<T> filter) {
        if (!isMongoFilter(filter)) {
            return null;
        }
        MongoFilter<T> mFilter = (MongoFilter<T>) filter;
        Function<MongoCollection<T>, Collection<T>> function = (coll -> {
            ArrayList<T> results = new ArrayList<>();
            coll.find(
                    mFilter.getFilter()
            ).sort(Sorts.ascending(mFilter.getKey()))
                    .forEach(results::add);
            return results;
        });
        return function.apply(collection);
    }

    @Override
    public Boolean removeObject(Filter<T> filter) {
        if (!isMongoFilter(filter)) {
            return null;
        }
        MongoFilter<T> mFilter = (MongoFilter<T>) filter;
        Function<MongoCollection<T>, Boolean> function = (coll ->
            coll.deleteOne(mFilter.getFilter()).wasAcknowledged()
        );
        return function.apply(collection);
    }

    @Override
    public Boolean removeObjects(Filter<T> filter) {
        if (!isMongoFilter(filter)) {
            return null;
        }
        MongoFilter<T> mFilter = (MongoFilter<T>) filter;
        Function<MongoCollection<T>, Boolean> function = (coll -> coll.deleteMany(mFilter.getFilter()).wasAcknowledged());
        return function.apply(collection);
    }

    private boolean isMongoFilter(Filter<T> filter) {
        return filter instanceof MongoFilter<T>;
    }
}
