package dev.samkist.prison;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class MineManager implements Listener {
    public String worldName;
    ArrayList<Mine> mines = new ArrayList<>();

    public MineManager(String worldName) {
        this.worldName = worldName;
    }
    @EventHandler
    public void blockPlace(BlockPlaceEvent e) { //WORLD CHECK FIRST
        Player player = e.getPlayer();
        Block block = e.getBlock();
        if (player.getWorld().getName().equalsIgnoreCase(this.worldName)) {
            if(!player.isOp()) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent e) { //WORLD CHECK FIRST
        Player player = e.getPlayer();
        Block block = e.getBlock();
        if (player.getWorld().getName().equalsIgnoreCase(this.worldName)) {
            @Nullable
            Mine m = insideMine(block);
            if(Objects.nonNull(m)) {
                if(!(player.isOp() || player.hasPermission(m.permission))) {
                    e.setCancelled(true);
                }
            }
        }
    }

    private Mine insideMine(Block block) {
        return mines.stream().filter(m -> m.containsBlock(block.getX(), block.getZ())).findFirst().orElse(null);
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
