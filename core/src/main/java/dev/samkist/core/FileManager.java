package dev.samkist.core;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileManager {

    private final Core plugin;
    private final Map<String, FileConfiguration> configFiles;

    public FileManager(Core plugin) {
        this.plugin = plugin;
        this.configFiles = new HashMap<>();

    }

    //Pertinent files that belong in the plugin folder
    private final String[] ymlFiles = {"config.yml"};
    private final String[] directories = {};

    //Initializes all files, checking if they are there
    public void initialize() {

        //Creates the Lumae folder in the plugins directory
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        // Check to see if all directories exist
        for (String directory : directories) {
            final Path folder = Paths.get(plugin.getDataFolder().toPath().toString(), directory);

            if (!Files.exists(folder)) {
                try {
                    Files.createDirectories(plugin.getDataFolder().toPath().resolve(directory));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Check if YML files exist, and load them
        for (String ymlFile : ymlFiles) {
            final Path path = Paths.get(plugin.getDataFolder().toPath().toString(), ymlFile);

            if (!Files.exists(path)) {
                try {
                    Files.copy(getClass().getClassLoader().getResourceAsStream(ymlFile), path);
                } catch (Exception e) {
                    e.printStackTrace();
                    plugin.getLogger().info("FAILED TO CREATE " + ymlFile);
                }
            }

            configFiles.put(ymlFile, YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), ymlFile)));
        }
    }

    public void reloadConfigurations() {
        for (String ymlFile : ymlFiles) {
            try {
                configFiles.get(ymlFile).load(new File(plugin.getDataFolder(), ymlFile));
            } catch (Exception e) {
            }
        }
    }

    public FileConfiguration getConfigYml() {
        return configFiles.get("config.yml");
    }
}
