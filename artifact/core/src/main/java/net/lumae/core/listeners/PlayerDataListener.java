package net.lumae.core.listeners;

import net.lumae.core.Core;
import net.lumae.core.data.DataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerDataListener implements Listener {

    private final Core core = JavaPlugin.getPlugin(Core.class);
    private final DataManager dataManager = core.getDataManager();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        dataManager.login(event.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        dataManager.loqout(event.getPlayer());
    }
}
