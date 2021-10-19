package dev.samkist.core.admin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/*
TODO: Might want to get a fetch player class to get an offline player if not online!
 */

public class PlayerStateModifiers implements CommandExecutor, Listener {
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
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=false)
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && this.isGodmode((Player)e.getEntity())) e.setCancelled(true);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        switch (command.getName()) {
            case "feed":
                if (args.length == 1) {
                    PlayerStateModifiers.setFoodLevel(Bukkit.getPlayer(args[0]), 20);
                } else {
                    PlayerStateModifiers.setFoodLevel((Player) sender, 20);
                }
                break;
            case "heal":
                if (args.length == 1) {
                    PlayerStateModifiers.setHealth(Bukkit.getPlayer(args[0]), 20.0);
                } else {
                    PlayerStateModifiers.setHealth((Player) sender, 20.0);
                }
                break;
            case "xp":
                if (args.length != 2 || args.length != 3) return false;
                switch(args[0]) {
                    case "get":
                        sender.sendMessage("[PluginSuite] Player "+Bukkit.getPlayer(args[1]).getName()+" is level "+String.valueOf(PlayerStateModifiers.getLevel(Bukkit.getPlayer(args[1])))+"!");
                        break;
                    case "set":
                        PlayerStateModifiers.setLevel(Bukkit.getPlayer(args[1]), Integer.valueOf(args[2]));
                        sender.sendMessage("[PluginSuite] You have set "+Bukkit.getPlayer(args[1]).getName()+"'s level to "+String.valueOf(PlayerStateModifiers.getLevel(Bukkit.getPlayer(args[1])))+"!");
                        break;
                    case "add":
                        PlayerStateModifiers.addLevel(Bukkit.getPlayer(args[1]), Integer.valueOf(args[2]));
                        sender.sendMessage("[PluginSuite] You have set "+Bukkit.getPlayer(args[1]).getName()+"'s level to "+String.valueOf(PlayerStateModifiers.getLevel(Bukkit.getPlayer(args[1])))+"!");
                        break;
                }
                break;
            case "give":
                if (args.length == 1) {
                    PlayerStateModifiers.give((Player)sender, args[0]);
                    ((Player)sender).sendMessage("[PluginSuite] You have recieved <1x"+Material.matchMaterial(args[0])+">!");
                } else if (args.length == 2) {
                    PlayerStateModifiers.give((Player)sender, args[0], Integer.valueOf(args[1]));
                    ((Player)sender).sendMessage("[PluginSuite] You have recieved <"+args[1]+"x"+Material.matchMaterial(args[0])+">!");
                } else if (args.length == 3) {
                    PlayerStateModifiers.give(Bukkit.getPlayer(args[0]), args[1], Integer.valueOf(args[2]));
                    ((Player)sender).sendMessage("[PluginSuite] You have gave <"+args[2]+"x"+Material.matchMaterial(args[1]).toString()+"> to "+args[0]+"!");
                } else {
                    return false;
                }
                break;
            case "cleanse":
                if (args.length == 1) {
                    PlayerStateModifiers.cleanse(Bukkit.getPlayer(args[0]));
                    ((Player)sender).sendMessage("[PluginSuite] You have cleansed "+args[0]+"!");
                } else {
                    PlayerStateModifiers.cleanse((Player)sender);
                    ((Player)sender).sendMessage("[PluginSuite] You have been cleansed!");
                }
                break;
            case "gamemode":
                if (args.length == 1) {
                    if (args[0].equals("1") || args[0].equals("creative") || args[0].equals("c")) {
                        PlayerStateModifiers.setCreative((Player)sender);
                    } else if (args[0].equals("0") || args[0].equals("survival") || args[0].equals("s")) {
                        PlayerStateModifiers.setSurvival((Player)sender);
                    } else return false;
                } else if (args.length == 2) {
                    if (args[1].equals("1") || args[1].equals("creative") || args[1].equals("c")) {
                        PlayerStateModifiers.setCreative(Bukkit.getPlayer(args[0]));
                    } else if (args[1].equals("0") || args[1].equals("survival") || args[1].equals("s")) {
                        PlayerStateModifiers.setSurvival(Bukkit.getPlayer(args[0]));
                    } else return false;
                } else {
                    PlayerStateModifiers.changeGamemode((Player)sender);
                }
                sender.sendMessage("[PluginSuite] Gamemode set!");
                break;
            case "enderchest":
                if (args.length == 1) {
                    PlayerStateModifiers.openInventory((Player)sender, Bukkit.getPlayer(args[0]));
                    ((Player)sender).sendMessage("[PluginSuite] You have opened "+Bukkit.getPlayer(args[0]).getName()+"'s enderchest!");
                } else {
                    PlayerStateModifiers.openEnderchest((Player)sender);
                    ((Player)sender).sendMessage("[PluginSuite] Enderchest opened!");
                }
                break;
            case "inventorysee":
                if (args.length != 1) return false;
                PlayerStateModifiers.openInventory((Player)sender, Bukkit.getPlayer(args[0]));
                ((Player)sender).sendMessage("[PluginSuite] You have opened "+Bukkit.getPlayer(args[0]).getName()+"'s inventory!");
            case "fly":
                if (args.length == 2) {
                    if (args[1].equals("1") || args[1].equals("true")) {
                        PlayerStateModifiers.enableFlight(Bukkit.getPlayer(args[0]));
                    } else if (args[1].equals("0") || args[1].equals("false")) {
                        PlayerStateModifiers.disableFlight(Bukkit.getPlayer(args[0]));
                    }
                } else if (args.length == 1) {
                    if (args[0].equals("1") || args[0].equals("true")) {
                        PlayerStateModifiers.enableFlight((Player)sender);
                    } else if (args[0].equals("0") || args[0].equals("false")) {
                        PlayerStateModifiers.disableFlight((Player)sender);
                    }
                } else {
                    PlayerStateModifiers.toggleFlight((Player)sender);
                }
                break;
            case "godmode":
                if (args.length == 2) {
                    if (args[1].equals("1") || args[1].equals("true")) {
                        this.enableGodmode(Bukkit.getPlayer(args[0]));
                    } else if (args[1].equals("0") || args[1].equals("false")) {
                        this.disableGodmode(Bukkit.getPlayer(args[0]));
                    } else return false;
                } else if (args.length == 1) {
                    if (args[0].equals("1") || args[0].equals("true")) {
                        this.enableGodmode((Player)sender);
                    } else if (args[0].equals("0") || args[0].equals("false")) {
                        this.disableGodmode((Player)sender);
                    } else return false;
                } else {
                    this.toggleGodmode((Player)sender);
                }
                break;
            default:
                return false;
        }
        return true;
    }
    public PlayerStateModifiers() {

    }
}
