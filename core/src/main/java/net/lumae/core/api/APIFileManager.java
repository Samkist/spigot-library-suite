package net.lumae.core.api;

import net.lumae.core.Core;
import net.lumae.core.data.local.FileManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class APIFileManager {
    private static final Core core = JavaPlugin.getPlugin(Core.class);
    private final FileManager fileManager;

    protected APIFileManager() {
        this.fileManager = core.getFileManager();
    }

    public FileConfiguration getConfig(String yml) {
        return fileManager.getConfiguration(yml);
    }
}
