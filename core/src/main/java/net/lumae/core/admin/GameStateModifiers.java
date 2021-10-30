package net.lumae.core.admin;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class GameStateModifiers implements Listener {
    public GameStateModifiers() {

    }
    public static void setTime(long time) {
        Bukkit.getServer().getWorld("world").setTime(time);
    }
}
