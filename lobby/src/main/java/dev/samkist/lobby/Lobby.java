package dev.samkist.lobby;

import dev.samkist.lobby.world.Interaction;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobby extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Interaction(), this);
    }
    @Override
    public void onDisable() {
    }
}
