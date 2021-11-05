package net.lumae.core.api;

import com.mongodb.client.MongoCollection;
import net.lumae.core.Core;
import net.lumae.core.data.entities.DatabaseObject;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;


public class APIDatabaseQuery<TResult extends DatabaseObject> implements APIResponse<Void, TResult>{

    private final Core core = JavaPlugin.getPlugin(Core.class);
    private final MongoCollection<TResult> collection;
    private final Function<MongoCollection<TResult>, TResult> query;
    private TResult result;


    protected APIDatabaseQuery(Function<MongoCollection<TResult>, TResult> query) {
        this.query = query;
        this.collection = (MongoCollection<TResult>) core.getDbManager().grabCollection(TResult.anInstance());
    }


    @Override
    public TResult queue() {
        this.result = query.apply(collection);
        return result;
    }

    @Override
    public TResult queue(BiConsumer<Void, TResult> success) {
        queue();
        success.accept(null, result);
        return result;
    }

    @Override
    public TResult queue(Consumer<TResult> success) {
        queue();
        success.accept(result);
        return result;
    }
}
