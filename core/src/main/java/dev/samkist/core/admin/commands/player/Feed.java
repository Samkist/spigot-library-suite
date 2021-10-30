package dev.samkist.core.admin.commands.player;

import dev.samkist.core.admin.PlayerStateModifiers;
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
            PlayerStateModifiers.setFoodLevel(Bukkit.getPlayer(args[0]), 20);
        } else {
            PlayerStateModifiers.setFoodLevel((Player) sender, 20);
        }
        return true;
    }
}
