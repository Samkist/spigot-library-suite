package net.lumae.core.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;

public class ChatInstance implements Listener {
    private class Instance {
        private final Player p;
        private ArrayList<String> commands = new ArrayList<String>();
        protected Instance(Player p, ArrayList<String> commands) {
            this.p = p;
            this.commands = commands;
        }
        protected boolean validCommand(String s) {
            if (this.commands.contains(s)) return true;
            return false;
        }
    }
    ArrayList<Instance> chat_instances = new ArrayList<Instance>();
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=false)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        for (int i = 0; i < chat_instances.size(); i++) {
            if (this.chat_instances.get(i).p == e.getPlayer() && !this.chat_instances.get(i).validCommand(e.getEventName())) {
                e.setCancelled(true);
            }
        }
    }
    public void create(Player p, ArrayList<String> commands) {
        this.chat_instances.add(new Instance(p, commands));
    }
    public void delete(Player p) {
        this.chat_instances.removeIf(instance -> (p == instance.p));
    }
}
