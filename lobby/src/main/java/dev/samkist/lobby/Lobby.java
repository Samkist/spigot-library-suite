package dev.samkist.lobby;

import dev.samkist.lobby.player.item.SpeedHacks;
import dev.samkist.lobby.player.Interactions;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobby extends JavaPlugin {
    /*
    Migrate to kits with these items.
     */
    private SpeedHacks speedHacks = new SpeedHacks();
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Interactions(), this);
        getServer().getPluginManager().registerEvents(speedHacks, this);
    }
    @Override
    public void onDisable() {
    }
}
