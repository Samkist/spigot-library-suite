package net.lumae.core.api;

import com.mongodb.client.MongoCollection;
import net.lumae.core.Core;
import net.lumae.core.data.database.DBManager;
import net.lumae.core.data.entities.DatabaseObject;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Function;

public class DatabaseAPI {
    private final Core core = JavaPlugin.getPlugin(Core.class);
    private final DBManager dbManager = core.getDbManager();

    protected DatabaseAPI() {

    }

    public <TResult extends DatabaseObject> APIResponse<Void, TResult> query(Function<MongoCollection<TResult>, TResult> function) {
        return new APIDatabaseQuery<>(function);
    }
}
