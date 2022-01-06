package net.lumae.core.api;

import net.lumae.core.Core;
import net.lumae.core.data.entities.LumaePlayer;
import net.lumae.core.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class APIPlayer {
    private final LumaePlayer lumaePlayer;
    private final Player player;
    private final Core core = JavaPlugin.getPlugin(Core.class);
    private final Economy economy = core.economy();
    private EconomyController economyController;
    private PlayerStateController playerStateController;

    protected APIPlayer(LumaePlayer lumaePlayer) {
        this.player = core.getServer().getPlayer(UUID.fromString(lumaePlayer.getUuid()));
        this.lumaePlayer = lumaePlayer;
        initialize();
    }

    protected APIPlayer(Player player) {
        this.player = player;
        this.lumaePlayer = core.getDataManager().fetchLumaePlayer(player);
        initialize();
    }

    private void initialize() {
        this.economyController = new EconomyController(player, economy);
        this.playerStateController = new PlayerStateController(player);
    }

    public EconomyController economyController() {
        return this.economyController;
    }

    public PlayerStateController playerStateController() {
        return this.playerStateController;
    }
}
