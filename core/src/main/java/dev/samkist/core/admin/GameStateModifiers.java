package dev.samkist.core.admin;

import dev.samkist.core.storage.ServerPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class GameStateModifiers implements CommandExecutor, Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=false)
    public static void onPlayerJoin(PlayerJoinEvent e) {
        ServerPlayer p = new ServerPlayer(e.getPlayer());
        if (p.isBanned()) {

        }
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
    public static void onBan(PlayerQuitEvent e) {
        if (new ServerPlayer(e.getPlayer()).isBanned()) {
            e.quitMessage(Component.text("[Player banned] PLAYER: "+e.getPlayer().getName()+", Reason: "+new ServerPlayer(e.getPlayer()).getBanReason()));
        }
    }
    public void ban(Player p, String reason) {
        String formattedReason;
        if (reason == "cheats") {
            formattedReason = "Use of a hacked client!";
        } else {
            formattedReason = "Reason not specified!";
        }
        new ServerPlayer(p).setBanned(true);
        new ServerPlayer(p).setBanReason(formattedReason);
        p.banPlayer(formattedReason);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        switch(command.getName()) {
            case "ban":
                if (args.length == 2) {
                    this.ban(Bukkit.getPlayer(args[0]).getPlayer(), args[1]);
                } else if (args.length == 1) {
                    this.ban(Bukkit.getPlayer(args[0]), "other");
                } else return false;
                break;
        }
        return true;
    }
}
