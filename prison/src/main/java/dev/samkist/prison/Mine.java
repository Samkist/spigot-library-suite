package dev.samkist.prison;

import com.sk89q.worldedit.regions.Region;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Mine {
    String name, permission;
    boolean pvp;
    Region mineRegion, zoneRegion;
    Location legendPosition, spawnPosition;
    ArrayList<Boss> bosses = new ArrayList<Boss>();
    ArrayList<Block> blocks = new ArrayList<Block>();
    public Mine(String name, String permission, boolean pvp, ArrayList<Block> blocks, ArrayList<Boss> bosses, Region mineRegion, Region zoneRegion, Location legendPosition, Location spawnPosition) {
        this.name = name;
        this.permission = permission;
        this.pvp = pvp;
        this.blocks = blocks;
        this.bosses = bosses;
        this.mineRegion = mineRegion;
        this.zoneRegion = zoneRegion;
        this.legendPosition = legendPosition;
        this.spawnPosition = spawnPosition;
    }
    public void teleportOutPlayer() {
        //TODO: Teleport all players outside of mine. To closest axis point outside so they don't get grouped.
    }
    public void draw() {
        //TODO: Use FAWE To set the prison blocks.
    }
    public void createLegend() {
        //TODO: Holographic name at po
    }
    public void teleport(Player p) {
        p.teleport(this.spawnPosition);
        p.sendMessage("[Prison] Teleported.");
    }
    public boolean containsBlock(int x, int y) {
        return this.mineRegion.contains(x, y);
    }
}
