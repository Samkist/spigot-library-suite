package dev.samkist.prison;

import com.sk89q.worldedit.regions.Region;
import net.lumae.core.data.util.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Mine implements Listener {
    String name, permission;
    boolean pvp, DISABLED;
    Region mineRegion, zoneRegion;
    Location legendPosition, spawnPosition;
    ArrayList<Boss> bosses = new ArrayList<Boss>();
    ArrayList<Pair<ItemStack, Double>> blockSpawn = new ArrayList<Pair<ItemStack, Double>>();  //Block and spawn percentage
    public Mine(String name, String permission, boolean pvp, ArrayList<Pair<ItemStack, Double>> blockSpawn, ArrayList<Boss> bosses, Region mineRegion, Region zoneRegion, Location legendPosition, Location spawnPosition) {
        this.name = name;
        this.permission = permission;
        this.pvp = pvp;
        this.blockSpawn = blockSpawn;
        this.bosses = bosses;
        this.mineRegion = mineRegion;
        this.zoneRegion = zoneRegion;
        this.legendPosition = legendPosition;
        this.spawnPosition = spawnPosition;
    }
    public void teleportPlayersOutMine() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (this.mineRegion.contains(p.getLocation().getBlockX(), p.getLocation().getBlockZ())) {
                p.teleport(this.spawnPosition);
            }
        }
        //TODO: Teleport player to closest axis point outside so they don't get grouped.
    }
    public void teleportPlayersOutZone() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            World world = p.getWorld();
            if (this.zoneRegion.contains(p.getLocation().getBlockX(), p.getLocation().getBlockZ())) {
                p.teleport(world.getSpawnLocation());
            }
        }
    }
    public void draw() {
        //TODO: Use FAWE To set the prison blocks.
    }
    public void createLegend() {
        //TODO: Holographic name
    }
    public void disable() {
        this.teleportPlayersOutZone();
        this.DISABLED = true;
        /*
        TODO: Add walk command listener, and dont allow entry.
        Teleport all players inside zone to spawn
        Send all players that were teleported a message saying the mine was disabled
         */
    }
    public void teleport(Player p) {
        p.teleport(this.spawnPosition);
        p.sendMessage("[Prison] Teleported.");
    }
}
