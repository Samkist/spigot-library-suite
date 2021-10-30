package net.lumae.core.admin.commands.world;

import net.lumae.core.admin.GameStateModifiers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Day implements CommandExecutor {
    public String command = "day";
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        GameStateModifiers.setTime(7);
        return true;
    }
}
