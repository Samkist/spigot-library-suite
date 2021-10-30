package net.lumae.core.listeners;

import net.lumae.core.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerDataListener implements Listener {

    private static final Core core = JavaPlugin.getPlugin(Core.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

    }
}
