package net.lumae.core;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.mongodb.MongoCredential;
import net.lumae.core.admin.GameStateModifiers;
import net.lumae.core.admin.PlayerStateModifiers;
import net.lumae.core.admin.commands.LumaeExecutor;
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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Core extends JavaPlugin {

    private final FileManager fileManager = new FileManager(this);
    private CoreAPI coreAPI;
    private DBManager dbManager;
    private DataManager dataManager;
    public static final long LAST_START_TIME = System.currentTimeMillis();

    @Override
    public void onEnable() {

        fileManager.load();

        configureDatabase();

        dataManager = new DataManager(fileManager, dbManager);

        coreAPI = CoreAPI.initialize(this);

        //State modifiers initializers
        PlayerStateModifiers playerStateModifiers = new PlayerStateModifiers();
        GameStateModifiers gameStateModifiers = new GameStateModifiers();
        PlayerCosmeticEvents playerCosmeticEvents = new PlayerCosmeticEvents();
        Economy economy = new Economy();
        ChatInstance chatInstance = new ChatInstance();

        //Command executors
        List<LumaeExecutor> commands = Arrays.asList(new Cleanse(), new Enderchest(),
                new Feed(), new Fly(playerStateModifiers), new Gamemode(), new Give(), new Godmode(playerStateModifiers),
                new Heal(), new Invsee(), new Xp(), new Day());

        //Events
        this.getServer().getPluginManager().registerEvents(chatInstance, this);
        this.getServer().getPluginManager().registerEvents(playerStateModifiers, this);
        this.getServer().getPluginManager().registerEvents(gameStateModifiers, this);
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
        /*LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("net.lumae.org.mongodb.driver");
        rootLogger.setLevel(Level.WARN);*/
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
