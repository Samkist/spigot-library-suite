package net.lumae.customsaddles.events;

import net.lumae.customsaddles.items.Saddle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.Map;

public class PlayerSaddleHandler implements Listener {
    Map<Player, Entity> activeRiders;
    @EventHandler
    public void rightClickCreature(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        Entity entity = e.getRightClicked();
        if (p.hasPermission("saddle.".concat(entity.getName())) && //Has
            p.getActiveItem() instanceof Saddle                 &&
            ((Saddle) p.getActiveItem()).isEntityType(entity.getType())
        ) {
            if (((Saddle) p.getActiveItem()).isOneTimeUse()) {
                p.getInventory().remove(p.getActiveItem());
                p.sendMessage("Your saddle has broken!");
            }
            activeRiders.put(p, entity);
            entity.addPassenger(p);
        }
    }
    @EventHandler
    public void onShift(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        if (activeRiders.containsKey(p)) activeRiders.get(p).removePassenger(p);
    }
}
