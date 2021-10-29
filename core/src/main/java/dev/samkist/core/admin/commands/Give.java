package dev.samkist.core.admin.commands;

import dev.samkist.core.admin.PlayerStateModifiers;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Give implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            PlayerStateModifiers.give((Player)sender, args[0]);
            ((Player)sender).sendMessage("[PluginSuite] You have recieved <1x"+ Material.matchMaterial(args[0])+">!");
        } else if (args.length == 2) {
            PlayerStateModifiers.give((Player)sender, args[0], Integer.valueOf(args[1]));
            ((Player)sender).sendMessage("[PluginSuite] You have recieved <"+args[1]+"x"+Material.matchMaterial(args[0])+">!");
        } else if (args.length == 3) {
            PlayerStateModifiers.give(Bukkit.getPlayer(args[0]), args[1], Integer.valueOf(args[2]));
            ((Player)sender).sendMessage("[PluginSuite] You have gave <"+args[2]+"x"+Material.matchMaterial(args[1]).toString()+"> to "+args[0]+"!");
        } else {
            return false;
        }
        return true;
    }
}
