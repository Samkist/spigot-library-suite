package dev.samkist.core;

/*
Creates a memorized chat instance with a player. Can store object to fiddle with.
 Freezes chat and ony allows interaction with server
 */

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import io.papermc.paper.event.player.AsyncChatEvent;

public class ChatInstance implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        //Stop all messages from anything but server
    }
    Object mem;
    Player player;
    ChatInstance(Player player, Object mem) {
        this.player = player;
        this.mem = mem;

    }
    public void sendMessage(String message) {
        this.player.sendMessage(message);
    }
    public Object getObj() {
        return this.mem;
    }
}
