package net.lumae.customsaddles;

import net.lumae.customsaddles.commands.GetSaddle;
import net.lumae.customsaddles.events.PlayerSaddleHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomSaddles extends JavaPlugin {
    @Override
    public void onEnable() {
        PlayerSaddleHandler saddleHandler = new PlayerSaddleHandler();
        getServer().getPluginManager().registerEvents(saddleHandler, this);
        getCommand("saddle").setExecutor(new GetSaddle());
    }

    @Override
    public void onDisable() {

    }
}
