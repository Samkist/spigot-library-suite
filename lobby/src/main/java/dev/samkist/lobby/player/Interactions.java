package dev.samkist.lobby.player;

import dev.samkist.lobby.player.item.SpeedHacks;
import dev.samkist.lobby.player.item.Teleporter;
import dev.samkist.lobby.player.item.Vanish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;

public class Interactions implements Listener {

    public static void setLoadout(Player p, String name) {
        PlayerInventory inventory = p.getInventory();
        inventory.clear();
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        switch(name) {
            default:
                items.add(44, new SpeedHacks());
                break;
        }
        for (ItemStack item : items) {
            inventory.addItem(item);
        }
    }

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
    public void onSpawn(PlayerJoinEvent e) {

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
