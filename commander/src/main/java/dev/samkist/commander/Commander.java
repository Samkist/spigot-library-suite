package dev.samkist.commander;

import dev.samkist.commander.commands.Feed;
import dev.samkist.commander.commands.Give;
import dev.samkist.commander.commands.Godmode;
import dev.samkist.commander.commands.SetXp;
import org.bukkit.plugin.java.JavaPlugin;

public class Commander extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new Godmode(), this);
        this.getCommand("give").setExecutor(new Give());
        this.getCommand("setxp").setExecutor(new SetXp());
        this.getCommand("godmode").setExecutor(new Godmode());
        this.getCommand("feed").setExecutor(new Feed());

    }

    @Override
    public void onDisable() {
    }
}
