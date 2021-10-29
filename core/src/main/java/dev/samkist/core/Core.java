package dev.samkist.core;

import com.mongodb.MongoCredential;
import dev.samkist.core.admin.*;
import dev.samkist.core.api.CoreAPI;
import dev.samkist.core.data.DataManager;
import dev.samkist.core.data.database.DBManager;
import dev.samkist.core.economy.Economy;
import dev.samkist.core.data.local.FileManager;
import dev.samkist.core.fun.PlayerCosmeticEvents;
import dev.samkist.core.utils.ChatInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Core extends JavaPlugin {

    private final CoreAPI coreAPI = new CoreAPI(this);
    private final FileManager fileManager = new FileManager(this);
    private DBManager dbManager;
    private DataManager dataManager;
    public static final long LAST_START_TIME = System.currentTimeMillis();

    @Override
    public void onEnable() {

        fileManager.initialize();

        //configureDatabase();

        dataManager = new DataManager(fileManager, dbManager);

        ChatInstance chatInstance = new ChatInstance();

        PlayerStateModifiers playerStateModifiers = new PlayerStateModifiers();
        GameStateModifiers gameStateModifiers = new GameStateModifiers();
        PlayerCosmeticEvents playerCosmeticEvents = new PlayerCosmeticEvents();
        Economy economy = new Economy();

        this.getServer().getPluginManager().registerEvents(chatInstance, this);
        this.getServer().getPluginManager().registerEvents(playerStateModifiers, this);
        this.getServer().getPluginManager().registerEvents(gameStateModifiers, this);
        //this.getServer().getPluginManager().registerEvents(playerCosmeticEvents, this);

        this.getCommand("give").setExecutor(playerStateModifiers);
        this.getCommand("xp").setExecutor(playerStateModifiers);
        this.getCommand("feed").setExecutor(playerStateModifiers);
        this.getCommand("heal").setExecutor(playerStateModifiers);
        this.getCommand("cleanse").setExecutor(playerStateModifiers);
        this.getCommand("gamemode").setExecutor(playerStateModifiers);
        this.getCommand("enderchest").setExecutor(playerStateModifiers);
        this.getCommand("inventorysee").setExecutor(playerStateModifiers);
        this.getCommand("fly").setExecutor(playerStateModifiers);
        this.getCommand("godmode").setExecutor(playerStateModifiers);
        this.getCommand("balance").setExecutor(economy);
        this.getCommand("baltop").setExecutor(economy);
        this.getCommand("pay").setExecutor(economy);
        this.getCommand("eco").setExecutor(economy);
        this.getCommand("ban").setExecutor(gameStateModifiers);
    }

    @Override
    public void onDisable() {
    }

    public FileManager getFileManager() {
        return this.fileManager;
    }

    public DBManager getDbManager() {
        return this.dbManager;
    }

    public DataManager getDataManager() {
        return this.dataManager;
    }

    private void configureDatabase() {
        final FileConfiguration configYml = fileManager.getConfigYml();
        final String connectionString = configYml.getString("database.connectionString");
        final String database = configYml.getString("database.name");
        if(Objects.nonNull(connectionString)) {
            dbManager = new DBManager(this, connectionString, database);
        } else {
            String user = configYml.getString("database.user");
            String password = configYml.getString("database.password");
            String host = configYml.getString("database.host");
            Integer port = configYml.getInt("database.port");

            dbManager = new DBManager(this, MongoCredential.createCredential(user, database, password.toCharArray()), host, database, port);
        }
    }

    public CoreAPI api() {
        return this.coreAPI;
    }
}
