package dev.samkist.core.listeners;

import dev.samkist.core.Core;
import dev.samkist.core.data.local.FileManager;
import dev.samkist.core.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinLeaveListener implements Listener {
    private static final Core plugin = JavaPlugin.getPlugin(Core.class);
    private final FileManager fileManager;
    //private final Message motd;

    public JoinLeaveListener() {
        this.fileManager = plugin.getFileManager();
        /*this.motd = plugin.getDataManager().messageById("lumae-motd")
                .orElse(new Message("lumae-motd",
                        fileManager.getConfigYml().getString("defaults.pluginMessages.motd.format")
                ));*/
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final String legacyMessage = ColorUtil.colorMessage("<$#50c878>" + player.getName() + " has arrived!<$#bddeec>");
        event.joinMessage(null);
        Bukkit.getConsoleSender().sendMessage(player.getName() + " joined");
        Bukkit.getServer().getOnlinePlayers().forEach(p -> player.sendMessage(legacyMessage));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final String legacyMessage = ColorUtil.colorMessage("<$#50c878>" + player.getName() + " has departed!<$#bddeec>");
        event.quitMessage(null);
        Bukkit.getConsoleSender().sendMessage(player.getName() + " left");
        Bukkit.getServer().getOnlinePlayers().forEach(p -> player.sendMessage(legacyMessage));
    }
}
