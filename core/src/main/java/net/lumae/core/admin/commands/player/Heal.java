package net.lumae.core.admin.commands.player;

import net.lumae.core.modifiers.PlayerStateModifiers;
import net.lumae.core.admin.commands.LumaeExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Heal extends LumaeExecutor {
    public Heal(String commandName) {
        super(commandName);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            PlayerStateModifiers.setHealth(Bukkit.getPlayer(args[0]), 20.0);
        } else {
            PlayerStateModifiers.setHealth((Player) sender, 20.0);
        }
        return true;
    }
}
