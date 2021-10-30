package net.lumae.core.api;

import net.lumae.core.Core;
import net.lumae.core.data.entities.LumaePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;

import java.util.Objects;

public class CoreAPI {

    private static CoreAPI coreAPI = null;
    private Core core;

    private CoreAPI(Core core) {
        this.core = core;
       coreAPI = this;
    }

    public static CoreAPI getInstance() {
        return coreAPI;
    }

    public static CoreAPI initialize(Core core) {
        return Objects.isNull(coreAPI) ? new CoreAPI(core) : null;
    }

    public APIPlayer getAPIPlayer(Player player) {
        return new APIPlayer(player);
    }

    public APIPlayer getAPIPlayer(LumaePlayer player) {
        return new APIPlayer(player);
    }

    public APIFileManager getFileManagerAPI() {
        return new APIFileManager();
    }
}
