package me.cbotte21.kits;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;

/*
NOT IMPLEMENTED.

GOALS / VISION:
    - /kit editor <name>
    - DELETE all chat messages to player.
    - Intercept all commands to server and allow no /
    - SHOW in chat...
        name: <name>
        name: <description>
        name: <permission>
        items:
            <...list...>>
     - On changes, clear chat and resend MSG
     - quit || exit || leave || end -> would you like to save? EXIT
     - save -> EXIT
 */

public class IngameEditor implements Listener {
    private ArrayList<Session> sessions = new ArrayList<Session>();
    private class Session {
        private Kit kit;
        private Player player;
        Session(Kit kit, Player player) {
            this.kit = kit;
            this.player = player;
        }
        public Kit getKit() {
            return this.kit;
        }
        public Player getPlayer() {
            return this.player;
        }
    }
    public void handleCommand(String name) { //TODO: check input from both EventHandlers
        switch(name) {

        }
    }
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (e.getPlayer().hasPermission("kits.editor")) {
            for (Session session : this.sessions) {
                if (session.getPlayer() == e.getPlayer()) {
                    this.handleCommand(e.getMessage());
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onMsg(AsyncChatEvent e) {
        if (e.getPlayer().hasPermission("kits.editor")) {
            for (Session session : this.sessions) {
                if (session.getPlayer() == e.getPlayer()) {
                    this.handleCommand(e.message().toString());
                    e.setCancelled(true);
                }
            }
        }
    }
}
