package dev.samkist.core;

import dev.samkist.core.admin.*;
import dev.samkist.core.economy.Economy;
import dev.samkist.core.utils.ChatInstance;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private final FileManager fileManager = new FileManager(this);
    public static final long LAST_START_TIME = System.currentTimeMillis();

    @Override
    public void onEnable() {
        Godmode godMode = new Godmode();
        ChatInstance chatInstance = new ChatInstance();

        this.getServer().getPluginManager().registerEvents(chatInstance, this);
        this.getServer().getPluginManager().registerEvents(godMode, this);

        //this.getCommand("godmode").setExecutor(godMode);

        this.getCommand("give").setExecutor(new PlayerStateModifiers());
        this.getCommand("xp").setExecutor(new PlayerStateModifiers());
        this.getCommand("feed").setExecutor(new PlayerStateModifiers());
        this.getCommand("heal").setExecutor(new PlayerStateModifiers());
        this.getCommand("cleanse").setExecutor(new PlayerStateModifiers());
        this.getCommand("gamemode").setExecutor(new PlayerStateModifiers());
        this.getCommand("enderchest").setExecutor(new PlayerStateModifiers());
        this.getCommand("inventorysee").setExecutor(new PlayerStateModifiers());
        this.getCommand("balance").setExecutor(new Economy());
        this.getCommand("baltop").setExecutor(new Economy());
        this.getCommand("pay").setExecutor(new Economy());
        this.getCommand("eco").setExecutor(new Economy());
        this.getCommand("teleport").setExecutor(new Teleport());
        this.getCommand("tphere").setExecutor(new Teleport());
    }

    @Override
    public void onDisable() {
    }
}
