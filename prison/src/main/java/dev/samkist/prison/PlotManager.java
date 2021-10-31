package dev.samkist.prison;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

/*
TODO:
 - Allow input of amount of plots.
 - Generate plots with FAWE. Loop through and make regions from [0,0,0] in flat world.

 */

public class PlotManager implements Listener {
    @EventHandler
    public void blockPlace(BlockPlaceEvent e) { //WORLD CHECK FIRST
        //check if on plot
        if (!this.getPlot(e.getBlock().getLocation()).isOwner(e.getPlayer())) { //Plot could be null
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void blockBreak(BlockBreakEvent e) { //WORLD CHECK FIRST
        if (!this.getPlot(e.getBlock().getLocation()).isOwner(e.getPlayer())) { //Plot could be null
            e.setCancelled(true);
        }
    }
    ArrayList<Plot> plots = new ArrayList<Plot>();
    public Plot getPlot(Location loc) {
        for (Plot plot : this.plots) {
            if (plot.region.contains(loc.getBlockX(), loc.getBlockY())) return plot;
        }
        return null;
    }
    public boolean purchase(Plot plot, Player p) {
        if (!plot.isOwned()) { //TODO: Check player money and withdraw
            plot.setOwner(p);
            p.sendMessage("[Prison] Plot purchased!");
            return true;
        }
        return false;
    }
    public void sell(Plot plot, Player p) {
        if (plot.getOwner() == p) {
            plot.sell();
            p.sendMessage("[Prison] Plot sold for $"+plot.getSellPrice()+".");
        } else {
            p.sendMessage("[Prison] You do not own this plot!");
        }
    }
}
