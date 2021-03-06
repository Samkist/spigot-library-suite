package me.cbotte21.kits;

import com.mongodb.client.MongoCollection;
import net.lumae.core.Core;
import net.lumae.core.api.*;
import net.lumae.core.data.entities.DatabaseObject;
import net.lumae.core.data.entities.LumaePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Function;

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
    private FileManagerAPI fileManager = coreApi.getFileManagerAPI();
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

        /*APIPlayer player = coreApi.getAPIPlayer(Bukkit.getPlayer("Samkist"));
        PlayerStateController playerStateController = player.playerStateController();
        APIResponse<Player, Integer> response = playerStateController.foodLevel(10);
        response.queue((p, foodLevel) -> {
            p.sendMessage("You're food level is now " + foodLevel);
        });*/

        /*
        The function supplied to the query method must be of generic type
        Function<MongoCollection<? extends DatabaseObject>, DatabaseObject>

        An example of a Database Object is LumaePlayer

        DatabaseAPI databaseApi = coreApi.getDatabaseAPI();
        Function<MongoCollection<LumaePlayer>, LumaePlayer> func = (coll) -> coll.find().first();
        APIResponse<Void, LumaePlayer> response = databaseApi.query(func);
        response.queue(p -> {
           //foo
        });*/
    }
    @Override
    public void onDisable() {

    }

}