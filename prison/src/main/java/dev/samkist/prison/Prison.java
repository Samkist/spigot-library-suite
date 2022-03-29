package dev.samkist.prison;

import net.lumae.core.Core;
import net.lumae.core.api.CoreAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class Prison extends JavaPlugin {
    private CoreAPI coreApi = JavaPlugin.getPlugin(Core.class).api();
    private PlotManager plotManager;
    private MineManager mineManager;
    WorldInteraction interaction;
    private Rankup rankup;
    @Override
    public void onEnable() {
        plotManager = new PlotManager(coreApi, "plots");
        mineManager = new MineManager("world");
        interaction = new WorldInteraction(mineManager, plotManager);
        rankup = new Rankup(coreApi);
        getServer().getPluginManager().registerEvents(interaction, this);
    }
    @Override
    public void onDisable() {
    }
}
