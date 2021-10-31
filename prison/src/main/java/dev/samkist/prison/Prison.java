package dev.samkist.prison;

import net.lumae.core.Core;
import net.lumae.core.api.CoreAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class Prison extends JavaPlugin {
    private CoreAPI coreApi = JavaPlugin.getPlugin(Core.class).api();
    @Override
    public void onEnable() {
        Rankup rankup = new Rankup(coreApi);
    }
    @Override
    public void onDisable() {
    }
}
