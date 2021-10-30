package net.lumae.core.api;

import net.lumae.core.Core;
import net.lumae.core.data.entities.LumaePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;

import java.util.Objects;

public class CoreAPI {

    private static final Core core = JavaPlugin.getPlugin(Core.class);
    private static CoreAPI coreAPI = null;

    private CoreAPI() {
       coreAPI = this;
    }

    public static CoreAPI getInstance() {
        if(Objects.isNull(coreAPI)) {
            new CoreAPI();
        }
        return coreAPI;
    }

    public APIPlayer getLumaePlayer(Player player) {
        return new APIPlayer(player);
    }

    public APIPlayer getLumaePlayer(LumaePlayer player) {
        return new APIPlayer(player);
    }

    public APIFileManager getFileManagerAPI() {
        return new APIFileManager();
    }
}
