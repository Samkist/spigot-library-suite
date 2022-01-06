package dev.samkist.prison;

import net.lumae.core.api.CoreAPI;
import net.lumae.core.api.EconomyController;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

/*
TODO:
 - Add optional builders / friends.

 */

public class PlotManager implements Listener {
    ArrayList<Plot> plots = new ArrayList<Plot>();
    CoreAPI coreAPI;
    String worldName;
    public PlotManager(CoreAPI coreAPI, String worldName) {
        this.coreAPI = coreAPI;
        this.worldName = worldName;
    }
    public Plot getPlot(Location loc) {
        return plots.stream().filter(p -> p.region.contains(loc.getBlockX(), loc.getBlockZ())).findFirst().orElse(null);
    }
    public boolean purchase(Plot plot, Player p) {
        EconomyController econP = coreAPI.getAPIPlayer(p).economyController();
        if (!plot.isOwned() && econP.attemptWithdraw(plot.getBuyPrice()).queue().get()) { //TODO: Check player money and withdraw
            plot.setOwner(p);
            save();
            return true;
        }
        return false;
    }
    public void sell(Plot plot) {
        //TODO: add economy support.
        plot.setOwner(null);
    }
    public boolean validPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Plot plot = getPlot(e.getPlayer().getLocation());
        return (p.getWorld().getName().equalsIgnoreCase(this.worldName) && plot.isOwner(e.getPlayer()));
    }
    public boolean validBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Plot plot = getPlot(e.getPlayer().getLocation());
        return (p.getWorld().getName().equalsIgnoreCase(this.worldName) && plot.isOwner(e.getPlayer()));
    }
    public void save() {
        //TODO: Upload plots to DB
    }
}
