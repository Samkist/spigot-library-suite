package dev.samkist.core.admin;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

/*
TODO: Might want to get a fetch player class to get an offline player if not online!
 */

public class PlayerStateModifiers implements Listener {
    public ArrayList<Player> ACTIVE_GODMODE = new ArrayList<Player>();
    public static void setHealth(Player p, double level) {
        p.setHealth(level);
    }
    public static void setFoodLevel(Player p, int level) {
        p.setFoodLevel(level);
    }
    public static int getLevel(Player p) {
        return p.getLevel();
    }
    public static void setLevel(Player p, int level) {
        p.setLevel(level);
    }
    public static void addLevel(Player p, int level) {
        PlayerStateModifiers.setLevel(p, PlayerStateModifiers.getLevel(p)+level);
    }
    public static void give(Player p, String itemName) {
        p.getInventory().addItem(new ItemStack(Material.matchMaterial(itemName)));
    }
    public static void give(Player p, String itemName, int amount) {
        p.getInventory().addItem(new ItemStack(Material.matchMaterial(itemName), amount));
    }
    public static void removePotionEffect(Player p, PotionEffectType type) {
        p.removePotionEffect(type);
    }
    public static void cleanse(Player p) {
        for (PotionEffectType type : PotionEffectType.values()) {
            PlayerStateModifiers.removePotionEffect(p, type);
        }
    }
    public static void setSurvival(Player p) {
        p.setGameMode(GameMode.SURVIVAL);
    }
    public static void setCreative(Player p) {
        p.setGameMode(GameMode.CREATIVE);
    }
    public static void changeGamemode(Player p) {
        if (p.getGameMode() != GameMode.SURVIVAL) {
            PlayerStateModifiers.setSurvival(p);
        } else {
            PlayerStateModifiers.setCreative(p);
        }
    }
    public static void openInventory(Player player, Inventory inventory) {
        player.openInventory(inventory);
    }
    public static void openInventory(Player player, Player supplier) {
        PlayerStateModifiers.openInventory(player, supplier.getInventory());
    }
    public static void openEnderchest(Player p) {
        PlayerStateModifiers.openInventory(p, p.getEnderChest());
    }
    public static void openEnderchest(Player toRecieve, Player toSupply) {
        PlayerStateModifiers.openInventory(toRecieve, toSupply.getEnderChest());
    }
    public static void enableFlight(Player p) {
        p.setAllowFlight(true);
        p.sendMessage("[PluginSuite] Flight enabled!");
    }
    public static void disableFlight(Player p) {
        p.setAllowFlight(false);
        p.sendMessage("[PluginSuite] Flight disabled!");
    }
    public static boolean isFlying(Player p) {
        return p.getAllowFlight();
    }
    public static void toggleFlight(Player p) {
        if (PlayerStateModifiers.isFlying(p)) {
            PlayerStateModifiers.disableFlight(p);
        } else {
            PlayerStateModifiers.enableFlight(p);
        }
    }
    public boolean isGodmode(Player p) {
        return this.ACTIVE_GODMODE.contains(p);
    }
    public void enableGodmode(Player p) {
        this.ACTIVE_GODMODE.add(p);
        p.sendMessage("[PluginSuite] Godmode enabled!");
    }
    public void disableGodmode(Player p) {
        this.ACTIVE_GODMODE.remove(p);
        p.sendMessage("[PluginSuite] Godmode disabled!");
    }
    public void toggleGodmode(Player p) {
        if (this.isGodmode(p)) {
            this.disableGodmode(p);
        } else {
            this.enableGodmode(p);
        }
    }
    public PlayerStateModifiers() {

    }
}
