package dev.samkist.core.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Feed implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            Bukkit.getPlayer(args[0]).setFoodLevel(20);
            Bukkit.getPlayer(args[0]).sendMessage("[COMMANDER] Appetite satisfied!");
        } else {
            ((Player)sender).setFoodLevel(20);
            ((Player)sender).sendMessage("[COMMANDER] Appetite satisfied!");
        }
        return true;
    }
}
