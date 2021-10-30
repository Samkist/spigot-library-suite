package net.lumae.core;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.mongodb.MongoCredential;
import net.lumae.core.admin.GameStateModifiers;
import net.lumae.core.admin.PlayerStateModifiers;
import net.lumae.core.admin.commands.player.*;
import net.lumae.core.admin.commands.world.Day;
import net.lumae.core.api.CoreAPI;
import net.lumae.core.data.DataManager;
import net.lumae.core.data.database.DBManager;
import net.lumae.core.economy.Economy;
import net.lumae.core.data.local.FileManager;
import net.lumae.core.fun.PlayerCosmeticEvents;
import net.lumae.core.utils.ChatInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Core extends JavaPlugin {

    private final FileManager fileManager = new FileManager(this);
    private CoreAPI coreAPI = CoreAPI.getInstance();
    private DBManager dbManager;
    private DataManager dataManager;
    public static final long LAST_START_TIME = System.currentTimeMillis();

    @Override
    public void onEnable() {

        fileManager.load();

        configureDatabase();

        dataManager = new DataManager(fileManager, dbManager);

        //State modifiers initializers
        PlayerStateModifiers playerStateModifiers = new PlayerStateModifiers();
        GameStateModifiers gameStateModifiers = new GameStateModifiers();
        PlayerCosmeticEvents playerCosmeticEvents = new PlayerCosmeticEvents();
        Economy economy = new Economy();
        ChatInstance chatInstance = new ChatInstance();

        //Admin commands
        //Player state
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
        //Game state
        Day day = new Day();

        //Events
        this.getServer().getPluginManager().registerEvents(chatInstance, this);
        this.getServer().getPluginManager().registerEvents(playerStateModifiers, this);
        this.getServer().getPluginManager().registerEvents(gameStateModifiers, this);
        //this.getServer().getPluginManager().registerEvents(playerCosmeticEvents, this);

        //Commands
        //Player State
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
        //Game State
        this.getCommand("day").setExecutor(day);
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
        LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("net.lumae.org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
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
