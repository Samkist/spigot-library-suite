package net.lumae.core.admin.commands.player;

import net.lumae.core.modifiers.PlayerStateModifiers;
import net.lumae.core.admin.commands.LumaeExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Feed extends LumaeExecutor {
    public Feed(String commandName) {
        super(commandName);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            PlayerStateModifiers.setFoodLevel(Bukkit.getPlayer(args[0]), 20);
        } else {
            PlayerStateModifiers.setFoodLevel((Player) sender, 20);
        }
        return true;
    }

    @Override
    public String commandName() {
        return "feed";
    }
}
