package dev.samkist.prison;

import com.sk89q.worldedit.regions.Region;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class Mine implements Listener {
    String name, permission;
    boolean pvp, DISABLED;
    Region mineRegion, zoneRegion;
    Location legendPosition, spawnPosition;
    ArrayList<Boss> bosses = new ArrayList<Boss>();
    ArrayList<MineBlock> mineBlocks = new ArrayList<MineBlock>();
    public Mine(String name, String permission, boolean pvp, ArrayList<MineBlock> mineBlocks, ArrayList<Boss> bosses, Region mineRegion, Region zoneRegion, Location legendPosition, Location spawnPosition) {
        this.name = name;
        this.permission = permission;
        this.pvp = pvp;
        this.mineBlocks = mineBlocks;
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
        Teleport all players inside zone to spawn
        Send all players that were teleported a message saying the mine was disabled
         */
    }
    public void teleport(Player p) {
        p.teleport(this.spawnPosition);
        p.sendMessage("[Prison] Teleported.");
    }
    public boolean containsBlock(int x, int z) {
        return this.mineRegion.contains(x, z);
    }
}
