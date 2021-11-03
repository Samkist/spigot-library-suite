package dev.samkist.prison;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

public class MineManager implements Listener {
    public String worldName;
    ArrayList<Mine> mines = new ArrayList<>();

    public MineManager(String worldName) {
        this.worldName = worldName;
    }
    @EventHandler
    public void blockPlace(BlockPlaceEvent e) { //WORLD CHECK FIRST
        Block block = e.getBlock();
        if (e.getPlayer().getWorld().getName() == this.worldName) {
            /*for (Mine mine : this.mines) {
                if (e.getPlayer().isOp() || (e.getPlayer().hasPermission(mine.permission) && mine.containsBlock(e.getBlock().getX(), e.getBlock().getZ()))) {
                    return;
                }
            }
            e.setCancelled(true);*/
            mines.stream().filter(m -> m.containsBlock(block.getX(), block.getZ()));
        }
    }
    @EventHandler
    public void blockBreak(BlockBreakEvent e) { //WORLD CHECK FIRST
        if (e.getPlayer().getWorld().getName() == this.worldName) {
            for (Mine mine : this.mines) {
                if (e.getPlayer().isOp() || (e.getPlayer().hasPermission(mine.permission) && mine.containsBlock(e.getBlock().getX(), e.getBlock().getZ()))) {
                    return;
                }
            }
            e.setCancelled(true);
        }
    }
    public boolean create(String name, String permission, MineBlock[] mineBlockSpawn, int minePos1, int minePos2, int zonePos1, int zonePos2, int legendLocation) {
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
