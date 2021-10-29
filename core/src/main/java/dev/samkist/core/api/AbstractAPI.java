package dev.samkist.core.api;

import dev.samkist.core.Core;
import dev.samkist.core.data.entities.ServerPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class AbstractAPI {
    private final Core core;

    public AbstractAPI(Core core) {
        this.core = core;
    }


   /*
    Switch ServerPlayer -> APIPlayer, APIPlayer methods update the player via internal logic
    public abstract ServerPlayer getPlayer(String username);
    public abstract ServerPlayer getPlayer(UUID uuid);
    public abstract ServerPlayer savePlayer(ServerPlayer player);
    public abstract ServerPlayer savePlayer(Player player);
    public abstract ServerPlayer banPlayer(ServerPlayer player);
    public abstract ServerPlayer banPlayer(Player player);*/
}
