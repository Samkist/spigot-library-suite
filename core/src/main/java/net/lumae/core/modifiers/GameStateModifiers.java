package net.lumae.core.modifiers;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class GameStateModifiers {
    public static void setTime(long time) {
        Bukkit.getServer().getWorld("world").setTime(time);
    }
    public static void setDay() { Bukkit.getServer().getWorld("world").setTime(7); }
}
