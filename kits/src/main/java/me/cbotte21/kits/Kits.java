package me.cbotte21.kits;

import net.lumae.core.Core;
import net.lumae.core.api.APIFileManager;
import net.lumae.core.api.CoreAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/*
NOTE: Have not tried running yet

TODO:
 - Have YAML cooldowns... Store [UUID, KIT_NAME, TIMESTAMP]... On kit.redeem, check check if on CD < 30 mins
 - Store items in kit, on YMLConn.save Loop through items and save give command name < ? mins
 - Add boolean DISABLED in KitManager.kits, scan for it on loop. < 5 mins
 - Possible add ALIASES, < 5 mins
 */

public class Kits extends JavaPlugin {
    private CoreAPI coreApi = JavaPlugin.getPlugin(Core.class).api();
    private APIFileManager fileManager = coreApi.getFileManagerAPI();
    private FileConfiguration config = fileManager.getConfig("kits.yml");
    @Override
    public void onEnable() {
        KitManager kitManager = new KitManager(config);
        this.getCommand("kit reload").setExecutor(kitManager);
        this.getCommand("kit redeem").setExecutor(kitManager);
        this.getCommand("kit save").setExecutor(kitManager);
        this.getCommand("kit delete").setExecutor(kitManager);
        this.getCommand("kit create").setExecutor(kitManager);
        this.config.getConfigurationSection("kit").getValues(false);
        this.fileManager.saveConfig("kits.yml");

    }
    @Override
    public void onDisable() {

    }

}