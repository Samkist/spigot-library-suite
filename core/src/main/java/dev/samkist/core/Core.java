package dev.samkist.core;

import com.mongodb.DB;
import dev.samkist.core.admin.*;
import dev.samkist.core.data.DataManager;
import dev.samkist.core.data.database.DBManager;
import dev.samkist.core.economy.Economy;
import dev.samkist.core.data.local.FileManager;
import dev.samkist.core.utils.ChatInstance;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private final FileManager fileManager = new FileManager(this);
    private DBManager dbManager;
    private DataManager dataManager;
    public static final long LAST_START_TIME = System.currentTimeMillis();

    @Override
    public void onEnable() {
        ChatInstance chatInstance = new ChatInstance();
        PlayerStateModifiers godmode = new PlayerStateModifiers();

        this.getServer().getPluginManager().registerEvents(chatInstance, this);
        this.getServer().getPluginManager().registerEvents(godmode, this);
        this.getServer().getPluginManager().registerEvents(new GameStateModifiers(), this);
        //this.getCommand("godmode").setExecutor(godmode);

        this.getCommand("give").setExecutor(new PlayerStateModifiers());
        this.getCommand("xp").setExecutor(new PlayerStateModifiers());
        this.getCommand("feed").setExecutor(new PlayerStateModifiers());
        this.getCommand("heal").setExecutor(new PlayerStateModifiers());
        this.getCommand("cleanse").setExecutor(new PlayerStateModifiers());
        this.getCommand("gamemode").setExecutor(new PlayerStateModifiers());
        this.getCommand("enderchest").setExecutor(new PlayerStateModifiers());
        this.getCommand("inventorysee").setExecutor(new PlayerStateModifiers());
        this.getCommand("fly").setExecutor(new PlayerStateModifiers());
        this.getCommand("godmode").setExecutor(godmode);
        this.getCommand("balance").setExecutor(new Economy());
        this.getCommand("baltop").setExecutor(new Economy());
        this.getCommand("pay").setExecutor(new Economy());
        this.getCommand("eco").setExecutor(new Economy());
        this.getCommand("ban").setExecutor(new GameStateModifiers());
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
}
