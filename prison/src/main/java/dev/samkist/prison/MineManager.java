package dev.samkist.prison;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

public class MineManager implements Listener {
    @EventHandler
    public void blockPlace(BlockPlaceEvent e) { //WORLD CHECK FIRST
            //check if on plot
        if (!e.getPlayer().isOp()) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void blockBreak(BlockBreakEvent e) { //WORLD CHECK FIRST
        for (Mine mine : this.mines) {
            if (e.getPlayer().isOp() || e.getPlayer().hasPermission(mine.permission) && mine.containsBlock(e.getBlock().getX(), e.getBlock().getY())) {
                return;
            }
        }
        e.setCancelled(true);
    }
    ArrayList<Mine> mines = new ArrayList<Mine>();
    public MineManager() {}
    public boolean create(String name, String permission, Block[] blockSpawn, int minePos1, int minePos2, int zonePos1, int zonePos2, int legendLocation) {
        //IF Mine exist return false
        this.save();
        return true;
    }
    public Mine get(String name) {
        for (Mine mine : this.mines) {
            if (mine.name == name) return mine;
        }
        return null;
    }
    public boolean delete(Mine mine) {
        if (this.mines.contains(mine)) {
            this.mines.remove(mine);
            this.save();
            return true;
        }
        return false;
    }
    public void save() {

    }
}
