package net.lumae.core.admin.commands.player;

import net.lumae.core.Core;
import net.lumae.core.modifiers.PlayerStateModifiers;
import net.lumae.core.admin.commands.LumaeExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Cleanse extends LumaeExecutor {

    public Cleanse(String commandName) {
        super(commandName);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            PlayerStateModifiers.cleanse(Bukkit.getPlayer(args[0]));
            ((Player)sender).sendMessage("[LunaeMC] You have cleansed "+args[0]+"!");
        } else {
            PlayerStateModifiers.cleanse((Player)sender);
            ((Player)sender).sendMessage("[LunaeMC] You have been cleansed!");
        }
        return true;
    }
}
