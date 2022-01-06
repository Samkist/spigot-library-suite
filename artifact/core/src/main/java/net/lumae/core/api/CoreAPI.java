package net.lumae.core.api;

import net.lumae.core.Core;
import net.lumae.core.data.entities.LumaePlayer;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

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

    public APIPlayer getAPIPlayer(UUID uuid) {
        return new APIPlayer(core.getServer().getPlayer(uuid));
    }

    public FileManagerAPI getFileManagerAPI() {
        return new FileManagerAPI();
    }

    public DatabaseAPI getDatabaseAPI() {
        return new DatabaseAPI();
    }
}
