package dev.samkist.commander.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetXp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1 && sender instanceof Player) {
            ((Player)sender).setLevel(Integer.valueOf(args[0]));
            return true;
        } else if (args.length == 2 && Bukkit.getPlayer(args[0]) instanceof Player) {
            Bukkit.getPlayer(args[0]).setLevel(Integer.valueOf(args[1]));
            return true;
        }
        return false;
    }
}
