package dev.samkist.core;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private final FileManager fileManager = new FileManager(this);
    public static final long LAST_START_TIME = System.currentTimeMillis();

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
