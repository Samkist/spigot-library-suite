package dev.samkist.core.api;

import dev.samkist.core.Core;
import dev.samkist.core.data.entities.ServerPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class APIPlayer {
    private final ServerPlayer serverPlayer;
    private final Player player;
    private static final Core core = JavaPlugin.getPlugin(Core.class);

    protected APIPlayer(ServerPlayer serverPlayer) {
        this.serverPlayer = serverPlayer;
        this.player = core.getServer().getPlayer(UUID.fromString(serverPlayer.getUuid()));
    }

    protected APIPlayer(Player player) {
        this.serverPlayer = core.getDataManager().fetchServerPlayer(player);
        this.player = player;
    }
}
