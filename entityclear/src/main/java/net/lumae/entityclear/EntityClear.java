package net.lumae.entityclear;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

/*
TODO: Needs functions to be moved out of main for api and command calls.
 */

import java.util.List;

public class EntityClear extends JavaPlugin {
    long INTERVAL = 20L;
    @Override
    public void onEnable() {
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getServer().getWorlds()) {
                    List<Entity> entList = world.getEntities();
                    for(Entity current : entList){
                        if (current.getType() == EntityType.DROPPED_ITEM){
                            current.remove();
                        }
                    }
                }
            }
        }, this.INTERVAL);
    }
    @Override
    public void onDisable() {
    }
}
