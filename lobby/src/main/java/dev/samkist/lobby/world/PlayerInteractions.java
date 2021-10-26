package dev.samkist.lobby.world;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class PlayerInteractions implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (!e.getPlayer().hasPermission("lobby.transmute") || !e.getPlayer().isOp()) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (!e.getPlayer().hasPermission("lobby.transmute") || !e.getPlayer().isOp()) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void creatureSpawn(CreatureSpawnEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void playerDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }
    @EventHandler //TODO: Check functionality
    public void emptyBucket(PlayerBucketEmptyEvent e) {
        e.setCancelled(true);
    }
}
