package net.lumae.entityclear;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

/*
TODO: Needs functions to be moved out of main for api and command calls.
 */

import java.util.List;

public class EntityClear extends JavaPlugin implements CommandExecutor {
    long INTERVAL = 20L;
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getPermission() == "entityclear.clear") {
            this.clearEntities();
            return true;
        }
        return false;
    }
    public void clearEntities() {
        for (World world : Bukkit.getServer().getWorlds()) {
            List<Entity> entList = world.getEntities();
            for(Entity current : entList){
                if (current.getType() == EntityType.DROPPED_ITEM){
                    current.remove();
                }
            }
        }
    }
    @Override
    public void onEnable() {
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                clearEntities();
            }
        }, this.INTERVAL);
    }
    @Override
    public void onDisable() {
    }
}
