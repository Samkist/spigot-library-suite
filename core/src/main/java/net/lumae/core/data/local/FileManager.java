package net.lumae.core.data.local;


import net.lumae.core.Core;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileManager {

    private final Core plugin;
    private final Map<String, FileConfiguration> configFiles;

    public FileManager(Core plugin) {
        this.plugin = plugin;
        this.configFiles = new HashMap<>();

    }

    //Pertinent files that belong in the plugin folder
    private final ArrayList<String> ymlFiles = new ArrayList<>(List.of("config.yml", "defaults.yml"));
    private final ArrayList<String> jsonFiles = new ArrayList<>(List.of("player-data.json"));
    private final String[] directories = {};

    //Initializes all files, checking if they are there
    public void load() {

        //Creates the Lumae folder in the plugins directory
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        loadYaml(ymlFiles);

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


       /* for(String jsonFile : jsonFiles) {
            final Path path = Paths.get(plugin.getDataFolder().toPath().resolve("data/" + jsonFile).toString());
            if(Files.notExists(path)) {
                try {
                    Files.copy(getClass().getClassLoader().getResourceAsStream("data/" + jsonFile), path);
                } catch (Exception e) {
                    e.printStackTrace();
                    plugin.getLogger().warning("FAILED TO CREATE " + jsonFile);
                }
            }
        }*/
    }

    public void loadYaml(String ymlFile) {
        loadYaml(List.of(ymlFile));
    }

    public void loadYaml(List<String> ymlFiles) {
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

    public Optional<FileConfiguration> reloadConfiguration(String yml) {
        try {
            getConfiguration(yml).load(new File(plugin.getDataFolder(), yml));
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.ofNullable(getConfiguration(yml));
    }

    public FileConfiguration getConfiguration(String yml) {
        if(configFiles.containsKey(yml)) {
            return configFiles.get(yml);
        } else {
            loadYaml(yml);
            return getConfiguration(yml);
        }
    }
    public Optional<FileConfiguration> saveConfig(String yml) {
        try {
            getConfiguration(yml).save(new File(plugin.getDataFolder(), yml));
        } catch(Exception e) {
            return Optional.empty();
        }
        return Optional.ofNullable(getConfiguration(yml));
    }

    public FileConfiguration getConfigYml() {
        return configFiles.get("config.yml");
    }

    public FileConfiguration getDefaultsYml() {
        return configFiles.get("defaults.yml");
    }
}
