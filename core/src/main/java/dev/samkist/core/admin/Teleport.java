package dev.samkist.core.admin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Teleport implements CommandExecutor {
    public static void teleport(Player origin, Player to) {
        origin.teleport(to);
    }
    public static void teleport(Player p, Location destination) {
        p.teleport(destination);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        switch (command.getName()) {
            case "tp":
                if (args.length == 4) {
                    Bukkit.getPlayer(args[0]).teleport(new Location(Bukkit.getWorld("world"), Double.valueOf(args[1]), Double.valueOf(args[2]), Double.valueOf(args[3])));
                } else if (args.length == 3) {
                    ((Player)sender).teleport(new Location(Bukkit.getWorld("world"), Double.valueOf(args[0]), Double.valueOf(args[1]), Double.valueOf(args[2])));
                }
                if (args.length == 2) {
                    Teleport.teleport(Bukkit.getPlayer(args[0]), Bukkit.getPlayer(args[1]));
                } else if (args.length == 1) {
                    Teleport.teleport((Player)sender, Bukkit.getPlayer(args[0]));
                } else return false;
                break;
            case "tphere":
                if (args.length != 1) return false;
                Teleport.teleport(Bukkit.getPlayer(args[0]), (Player)sender);
                break;
        }
        return true;
    }
}
