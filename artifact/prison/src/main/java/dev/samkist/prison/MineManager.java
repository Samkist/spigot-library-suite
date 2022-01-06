package dev.samkist.prison;

import com.sk89q.worldedit.regions.Region;
import net.lumae.core.data.util.Pair;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Objects;

public class MineManager implements Listener {
    ArrayList<Mine> mines = new ArrayList<>();
    public String worldName;

    public MineManager(String worldName) {
        this.worldName = worldName;
    }

    public boolean create(String name, String permission) {
        ArrayList<Pair<ItemStack, Double>> blockSpawn = new ArrayList<>();
        blockSpawn.add(new Pair<ItemStack, Double>(new ItemStack(Material.STONE), 1.0));
        return create(name, permission, false, blockSpawn, new ArrayList<Boss>(), null, null, null, null);
    }
    public boolean create(String name, String permission, boolean pvp, ArrayList<Pair<ItemStack, Double>> blockSpawn, ArrayList<Boss> bosses, Region mineRegion, Region zoneRegion, Location spawnLocation, Location legendLocation) {
        if (Objects.nonNull(get(name))) return false;
        this.mines.add(new Mine(name, permission, pvp, blockSpawn, bosses, mineRegion, zoneRegion, legendLocation, spawnLocation));
        this.save();
        return true;
    }
    public Mine get(String name) {
        return mines.stream().filter(m -> m.name == name).findFirst().orElse(null);
    }
    public boolean delete(Mine mine) {
        if (this.mines.contains(mine)) {
            this.mines.remove(mine);
            this.save();
            return true;
        }
        return false;
    }
    private Mine insideMine(Block block) {
        return mines.stream().filter(m -> m.mineRegion.contains(block.getX(), block.getZ())).findFirst().orElse(null);
    }
    public boolean validPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        return (p.getWorld().getName().equalsIgnoreCase(this.worldName) && (p.isOp() || p.hasPermission("prison.alter")));
    }
    public boolean validBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        if (player.getWorld().getName().equalsIgnoreCase(this.worldName)) {
            @Nullable
            Mine m = insideMine(block);
            if(Objects.nonNull(m)) {
                if(!(player.isOp() || player.hasPermission(m.permission))) {
                    return false;
                }
            }
        }
        return true;
    }
    public void save() {
        //TODO: Upload mines to db
    }
}
