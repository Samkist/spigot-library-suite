package net.lumae.core.api;

import net.lumae.core.Core;
import net.lumae.core.data.entities.LumaePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class APIPlayer {
    private final LumaePlayer lumaePlayer;
    private final Player player;
    private final Core core = JavaPlugin.getPlugin(Core.class);

    protected APIPlayer(LumaePlayer lumaePlayer) {
        this.lumaePlayer = lumaePlayer;
        this.player = core.getServer().getPlayer(UUID.fromString(lumaePlayer.getUuid()));
    }

    protected APIPlayer(Player player) {
        this.lumaePlayer = core.getDataManager().fetchLumaePlayer(player);
        this.player = player;
    }
}
