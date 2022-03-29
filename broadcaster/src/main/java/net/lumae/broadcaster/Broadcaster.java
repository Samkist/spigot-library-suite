package net.lumae.broadcaster;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Broadcaster extends JavaPlugin {
    Messages messages = new Messages();
    @Override
    public void onEnable() {
        this.messages.populate();
        Bukkit.getConsoleSender().sendMessage(this.messages.messages.size()+" broadcasts loaded!");
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.getServer().sendMessage(messages.getMessage());
            }
        }, this.messages.INTERVAL);
    }
    @Override
    public void onDisable() {
    }

}
