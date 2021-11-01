package net.lumae.core.listeners;

import net.lumae.core.Core;
import net.lumae.core.data.local.FileManager;
import net.lumae.core.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinLeaveListener implements Listener {
    private final Core core = JavaPlugin.getPlugin(Core.class);
    private final FileManager fileManager;
    //private final Message motd;

    public JoinLeaveListener() {
        this.fileManager = core.getFileManager();
        /*this.motd = plugin.getDataManager().messageById("lumae-motd")
                .orElse(new Message("lumae-motd",
                        fileManager.getConfigYml().getString("defaults.pluginMessages.motd.format")
                ));*/
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final String legacyMessage = ColorUtil.colorMessage("<GRADIENT:50c878>" + player.getName() + " has arrived!</GRADIENT:bddeec>");
        event.joinMessage(null);
        Bukkit.getConsoleSender().sendMessage(player.getName() + " joined");
        Bukkit.getServer().getOnlinePlayers().forEach(p -> player.sendMessage(legacyMessage));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final String legacyMessage = ColorUtil.colorMessage("<GRADIENT:50c878>" + player.getName() + " has departed!</GRADIENT:bddeec>");
        event.quitMessage(null);
        Bukkit.getConsoleSender().sendMessage(player.getName() + " left");
        Bukkit.getServer().getOnlinePlayers().forEach(p -> player.sendMessage(legacyMessage));
    }
}
