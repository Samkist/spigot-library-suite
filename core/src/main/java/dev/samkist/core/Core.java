package dev.samkist.core;

import com.mongodb.MongoCredential;
import dev.samkist.core.admin.*;
import dev.samkist.core.admin.commands.*;
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

        //Admin commands
        Cleanse cleanse = new Cleanse();
        Enderchest enderchest = new Enderchest();
        Feed feed = new Feed();
        Fly fly = new Fly(playerStateModifiers);
        Gamemode gameMode = new Gamemode();
        Give give = new Give();
        Godmode godmode = new Godmode(playerStateModifiers);
        Heal heal = new Heal();
        Invsee invsee = new Invsee();
        Xp xp = new Xp();
        //END

        this.getServer().getPluginManager().registerEvents(chatInstance, this);
        this.getServer().getPluginManager().registerEvents(playerStateModifiers, this);
        this.getServer().getPluginManager().registerEvents(gameStateModifiers, this);
        //this.getServer().getPluginManager().registerEvents(playerCosmeticEvents, this);

        this.getCommand("give").setExecutor(give);
        this.getCommand("xp").setExecutor(xp);
        this.getCommand("feed").setExecutor(feed);
        this.getCommand("heal").setExecutor(heal);
        this.getCommand("cleanse").setExecutor(cleanse);
        this.getCommand("gamemode").setExecutor(gameMode);
        this.getCommand("enderchest").setExecutor(enderchest);
        this.getCommand("inventorysee").setExecutor(invsee);
        this.getCommand("fly").setExecutor(fly);
        this.getCommand("godmode").setExecutor(godmode);
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
