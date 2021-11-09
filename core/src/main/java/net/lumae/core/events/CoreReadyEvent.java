package net.lumae.core.events;

import net.lumae.core.api.CoreAPI;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CoreReadyEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();
    private final CoreAPI api;

    public CoreReadyEvent(CoreAPI api) {
        this.api = api;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    public CoreAPI getApi() {
        return api;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
