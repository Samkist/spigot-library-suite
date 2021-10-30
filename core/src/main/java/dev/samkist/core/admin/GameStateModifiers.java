package dev.samkist.core.admin;

import dev.samkist.core.data.entities.ServerPlayer;
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

public class GameStateModifiers implements Listener {
    public GameStateModifiers() {

    }
    public static void setTime(long time) {
        Bukkit.getServer().getWorld("world").setTime(time);
    }
}
