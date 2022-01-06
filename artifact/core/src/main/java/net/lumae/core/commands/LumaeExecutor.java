package net.lumae.core.commands;

import net.lumae.core.Core;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class LumaeExecutor implements CommandExecutor {
    private final String commandName;
    private final Core core;

    public LumaeExecutor(String commandName) {
        this.commandName = commandName;
        core = JavaPlugin.getPlugin(Core.class);
    }

    public String commandName() {
        return commandName;
    }

    protected Core getCore() {
        return core;
    }
}
