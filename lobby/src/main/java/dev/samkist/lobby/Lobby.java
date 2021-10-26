package dev.samkist.lobby;

import dev.samkist.lobby.items.SpeedHacks;
import dev.samkist.lobby.world.PlayerInteractions;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobby extends JavaPlugin {
    private SpeedHacks speedHacks = new SpeedHacks();
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerInteractions(), this);
        getServer().getPluginManager().registerEvents(speedHacks, this);
    }
    @Override
    public void onDisable() {
    }
}
