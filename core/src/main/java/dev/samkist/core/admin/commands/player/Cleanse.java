package dev.samkist.core.admin.commands.player;

import dev.samkist.core.admin.PlayerStateModifiers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Cleanse implements CommandExecutor {
    public String command = "cleanse";
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            PlayerStateModifiers.cleanse(Bukkit.getPlayer(args[0]));
            ((Player)sender).sendMessage("[PluginSuite] You have cleansed "+args[0]+"!");
        } else {
            PlayerStateModifiers.cleanse((Player)sender);
            ((Player)sender).sendMessage("[PluginSuite] You have been cleansed!");
        }
        return true;
    }
}
