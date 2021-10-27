package dev.samkist.lobby.player.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.util.ArrayList;
import java.util.List;

public class SpeedHacks extends ItemStack implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getItem() == this) {
            this.toggleSpeed(e.getPlayer());
        }
    }
    @EventHandler
    public void onSpawn(PlayerSpawnLocationEvent e) {
        e.getPlayer().getInventory().addItem(this);
    }
    public SpeedHacks() {
        //Amount && Item
        this.setAmount(1);
        this.setType(Material.POTION);
        //Add lore
        List<Component> lore = new ArrayList<Component>();
        lore.add(Component.text(":|: SpeedHacks :|:"));
        this.lore(lore);
    }
    public void toggleSpeed(Player p) {
        if (p.getWalkSpeed() != 0.2F) {
            this.disableSpeed(p);
        } else {
            this.enableSpeed(p);
        }
    }
    public void enableSpeed(Player p) {
        p.sendMessage("[Lobby] Speedhacks activated!");
        p.setWalkSpeed(0.6F);
    }
    public void disableSpeed(Player p) {
        p.sendMessage("[Lobby] Speedhacks disabled!");
        p.setWalkSpeed(.2F);
    }
}
