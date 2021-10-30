package net.lumae.core.admin.commands;

import org.bukkit.command.CommandExecutor;

public interface LumaeExecutor extends CommandExecutor {
    String commandName();
}
