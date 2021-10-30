package net.lumae.core.admin.commands.player;

import net.lumae.core.admin.PlayerStateModifiers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Enderchest implements CommandExecutor {
    public String command = "enderchest";
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            PlayerStateModifiers.openInventory((Player)sender, Bukkit.getPlayer(args[0]));
            ((Player)sender).sendMessage("[PluginSuite] You have opened "+Bukkit.getPlayer(args[0]).getName()+"'s enderchest!");
        } else {
            PlayerStateModifiers.openEnderchest((Player)sender);
            ((Player)sender).sendMessage("[PluginSuite] Enderchest opened!");
        }
        return true;
    }
}
