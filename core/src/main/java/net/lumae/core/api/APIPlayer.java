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
    private final EconomyController economyController;

    protected APIPlayer(LumaePlayer lumaePlayer) {
        this.lumaePlayer = lumaePlayer;
        this.player = core.getServer().getPlayer(UUID.fromString(lumaePlayer.getUuid()));
        this.economyController = new EconomyController(player, economy);
    }

    protected APIPlayer(Player player) {
        this.lumaePlayer = core.getDataManager().fetchLumaePlayer(player);
        this.player = player;
        this.economyController = new EconomyController(player, economy);
    }

    public EconomyController economyController() {
        return this.economyController;
    }


}
