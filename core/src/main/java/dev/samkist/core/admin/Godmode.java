package dev.samkist.core.admin;

/*
TODO: Not working.
 */

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Godmode implements CommandExecutor, Listener {
    ArrayList<Player> GODS = new ArrayList<Player>();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (GODS.contains((Player)sender)) {
                GODS.remove((Player)sender);
            } else {
                GODS.add((Player) sender);
                ((Player)sender).sendMessage("Player added: "+GODS.toString());
            }
            ((Player)sender).sendMessage("Godmode "+(GODS.contains((Player)sender) ? "enabled!" : "disabled!"));
            return true;
        }
        return false;
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=false)
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && GODS.contains((Player)e.getEntity())) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (GODS.contains((Player)e.getPlayer())) {
            ((Player)e.getPlayer()).sendMessage("[COMMANDER] Godmode is enabled!");
        }
    }
}
