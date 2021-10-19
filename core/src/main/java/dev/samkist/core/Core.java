package dev.samkist.core;

import dev.samkist.core.admin.*;
import dev.samkist.core.economy.Economy;
import dev.samkist.core.utils.ChatInstance;
import org.bukkit.Bukkit;
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
        this.getCommand("give").setExecutor(new Give());
        this.getCommand("setxp").setExecutor(new SetXp());
        this.getCommand("godmode").setExecutor(godMode);
        this.getCommand("feed").setExecutor(new Feed());
        this.getCommand("heal").setExecutor(new Heal());
        getCommand("balance").setExecutor(new Economy());
        getCommand("baltop").setExecutor(new Economy());
        getCommand("pay").setExecutor(new Economy());
        getCommand("eco").setExecutor(new Economy());
    }

    @Override
    public void onDisable() {
    }
}
