package net.lumae.core.modifiers;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

/*
TODO: Might want to get a fetch player class to get an offline player if not online!
 */

public class PlayerStateModifiers {
    public static ArrayList<Player> ACTIVE_GODMODE = new ArrayList<>();
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
    }
    public static void disableFlight(Player p) {
        p.setAllowFlight(false);
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
    public static boolean isGodmode(Player p) {
        return ACTIVE_GODMODE.contains(p);
    }
    public static void enableGodmode(Player p) {
        ACTIVE_GODMODE.add(p);
    }
    public static void disableGodmode(Player p) {
        ACTIVE_GODMODE.remove(p);
    }
    public static void toggleGodmode(Player p) {
        if (isGodmode(p)) {
            disableGodmode(p);
        } else {
            enableGodmode(p);
        }
    }
    public PlayerStateModifiers() {

    }
}
