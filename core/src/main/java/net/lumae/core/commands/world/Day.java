package net.lumae.core.commands.world;

import net.lumae.core.commands.LumaeExecutor;
import net.lumae.core.modifiers.GameStateModifiers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Day extends LumaeExecutor {
    public Day(String commandName) {
        super(commandName);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        GameStateModifiers.setTime(7);
        return true;
    }

    @Override
    public String commandName() {
        return "day";
    }
}
