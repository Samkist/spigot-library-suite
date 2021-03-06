package net.lumae.core;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.MongoCredential;
import net.lumae.core.commands.LumaeExecutor;
import net.lumae.core.commands.player.*;
import net.lumae.core.commands.world.Day;
import net.lumae.core.api.CoreAPI;
import net.lumae.core.data.DataManager;
import net.lumae.core.data.database.DBManager;
import net.lumae.core.data.local.FileManager;
import net.lumae.core.economy.Economy;
import net.lumae.core.events.CoreReadyEvent;
import net.lumae.core.listeners.JoinLeaveListener;
import net.lumae.core.listeners.PlayerDataListener;
import net.lumae.core.modifiers.PlayerStateModifiers;
import net.lumae.core.utils.ChatInstance;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Core extends JavaPlugin {

    private final FileManager fileManager = new FileManager(this);
    private CoreAPI coreAPI;
    private DBManager dbManager;
    private DataManager dataManager;
    private Economy economy;
    public static final long LAST_START_TIME = System.currentTimeMillis();

    @Override
    public void onEnable() {

        fileManager.load();

        configureDatabase();

        dataManager = new DataManager(fileManager, dbManager);

        dbManager.initialize();

        dataManager.initialize();

        coreAPI = CoreAPI.initialize(this);

        CoreReadyEvent readyEvent = new CoreReadyEvent(coreAPI);

        Bukkit.getPluginManager().callEvent(readyEvent);

        economy = new Economy();
        //Listener initializers
        PlayerDataListener playerDataListener = new PlayerDataListener();
        JoinLeaveListener joinLeaveListener = new JoinLeaveListener();

        //State modifiers initializers
        PlayerStateModifiers playerStateModifiers = new PlayerStateModifiers();
        ChatInstance chatInstance = new ChatInstance();
        EconomyCommand economyCommand = new EconomyCommand("economy", economy);
        List<String> economyCommands = List.of("balance", "baltop", "pay", "economy");

        economyCommands.forEach(name -> getCommand(name).setExecutor(economyCommand));

        //Command executors
        List<LumaeExecutor> commands = Arrays.asList(
                new Cleanse("cleanse"),
                new Enderchest("enderchest"),
                new Feed("feed"),
                new Fly("fly", playerStateModifiers),
                new Gamemode("gamemode"),
                new Give("give"),
                new Godmode("godmode", playerStateModifiers),
                new Heal("heal"),
                new Invsee("invsee"),
                new Xp("xp"),
                new Day("day")
        );

        //Events
        this.getServer().getPluginManager().registerEvents(playerDataListener, this);
        this.getServer().getPluginManager().registerEvents(joinLeaveListener, this);
        this.getServer().getPluginManager().registerEvents(chatInstance, this);
        //this.getServer().getPluginManager().registerEvents(playerCosmeticEvents, this);

        commands.forEach(cmd -> this.getCommand(cmd.commandName()).setExecutor(cmd));

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
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("net.lumae.org.mongodb.driver");
        rootLogger.setLevel(Level.WARN);
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

    public Economy economy() {
        return this.economy;
    }
}
