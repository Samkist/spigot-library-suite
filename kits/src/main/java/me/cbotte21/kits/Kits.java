package me.cbotte21.kits;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/*
NOTE: Have not tried running yet

TODO:
 - Have YAML cooldowns... Store [UUID, KIT_NAME, TIMESTAMP]... On kit.redeem, check check if on CD
 - Store items in kit, on YMLConn.save Loop through items and save give command name
 */

public class Kits extends JavaPlugin {
    FileConfiguration config = getConfig();
    @Override
    public void onEnable() {
        KitManager kitManager = new KitManager(config);
        this.getCommand("kit reload").setExecutor(kitManager);
        this.getCommand("kit redeem").setExecutor(kitManager);
        this.getCommand("kit save").setExecutor(kitManager);
        this.getCommand("kit create").setExecutor(kitManager);
        this.getConfig().getConfigurationSection("kit").getValues(false);
        this.config.options().copyDefaults(true);
        this.saveConfig();
    }
    @Override
    public void onDisable() {

    }

}