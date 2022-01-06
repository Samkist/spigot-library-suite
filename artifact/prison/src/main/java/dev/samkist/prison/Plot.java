package dev.samkist.prison;

import com.sk89q.worldedit.regions.Region;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Plot {
    Player owner = null;
    Region region;
    double price;
    public Plot() {}
    public boolean isOwned() {
        return (this.owner == null);
    }
    public void setOwner(Player p) {
        this.owner = p;
    }
    public Player getOwner() {
        return this.owner;
    }
    public boolean isOwner(Player p) {
        return this.owner == p;
    }
    public double getBuyPrice() { return this.price; }
    public double getSellPrice() {
        return this.price*.7;
    }
    public void sell() {
        //add getSellPrice to owner
        this.owner = null;
        this.clear();
    }
    public void teleport(Player p) {
        p.teleport(new Location(Bukkit.getWorld(region.getWorld().getName()), region.getMinimumPoint().getBlockX(), region.getMinimumPoint().getBlockY(), 0));
        p.sendMessage("[Prison] Teleported to plot.");
    }
    public void clear() {
        //TODO: clear plot with FAWE and region.
    }
}
