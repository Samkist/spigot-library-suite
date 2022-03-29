package net.lumae.core.commands.player;

import net.lumae.core.modifiers.PlayerStateModifiers;
import net.lumae.core.commands.LumaeExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Invsee extends LumaeExecutor {
    public Invsee(String commandName) {
        super(commandName);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 1) return false;
        PlayerStateModifiers.openInventory((Player)sender, Bukkit.getPlayer(args[0]));
        sender.sendMessage("You have opened "+Bukkit.getPlayer(args[0]).getName()+"'s inventory!");
        return true;
    }

}
