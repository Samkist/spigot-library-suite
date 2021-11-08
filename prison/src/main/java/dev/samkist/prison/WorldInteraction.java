package dev.samkist.prison;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class WorldInteraction implements Listener {
    PlotManager plotManager;
    MineManager mineManager;
    public WorldInteraction(MineManager mineManager, PlotManager plotManager) {
        this.mineManager = mineManager;
        this.plotManager = plotManager;
    }
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        e.setCancelled(mineManager.validBreak(e) || plotManager.validBreak(e));
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        e.setCancelled(mineManager.validPlace(e) || plotManager.validPlace(e)); // OR plotManager.validPlace(e);
    }
}
