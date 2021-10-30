package dev.samkist.core.admin.commands.world;

import dev.samkist.core.admin.GameStateModifiers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Day implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        GameStateModifiers.setTime(7);
        return true;
    }
}
