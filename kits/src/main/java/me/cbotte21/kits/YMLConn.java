package me.cbotte21.kits;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class YMLConn {
    public static ArrayList<Kit> getKits() {
        return new ArrayList<Kit>();
    }
    public static void save(ArrayList<Kit> kit, FileConfiguration config) {
        //TODO: Loop through kits in array and save kits in YML
        /*
        String name = kit.getName(), description = kit.getDescription(), permission = kit.getPermission();
        int cooldown = kit.cooldown;
        //If name exist in YAML, delete... SAVE
        config.set("kits", name);
        config.get("kits.".concat(name), "description");
        config.get("kits.".concat(name), "cooldown");
        config.get("kits.".concat(name), "items");
        Bukkit.getConsoleSender().sendMessage("[Kits] Kit saved! [ "+kit.getName()+" ]");
        */
    }
         */
}
