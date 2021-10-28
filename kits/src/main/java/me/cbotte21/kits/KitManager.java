package me.cbotte21.kits;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class KitManager implements CommandExecutor {
    public ArrayList<Kit> kits = new ArrayList<Kit>();
    private FileConfiguration config;
    public KitManager(FileConfiguration config) {
        this.config = config;
        this.reload();
    }
    public void reload() {
        this.kits = YMLConn.getKits();
    }
    public boolean create(String name, String description) {
        for (Kit kit : this.kits) { //Kit already exists
            if (kit.name == name) return false;
        }
        YMLConn.save(new Kit(name, description, "kits.".concat(name)), this.config);
        this.save();
        this.reload();
        return true;
    }
    public Kit getKit(String name) {
        for (Kit kit : this.kits) {
            if (kit.name == name) return kit;
        }
        return null;
    }
    public void deleteKit(Kit kit) {
        this.kits.remove(kit);
    }
    public void deleteKit(String name) {
        for (Kit kit : this.kits) {
            if (kit.name == name) this.kits.remove(kit);
        }
    }
    public void save() {
        YMLConn.save(this.kits, config);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        switch (command.getPermission()) {
            case "kits.reload":
                this.reload();
                return true;
            case "kits.redeem":
                String permission = "kits.".concat(args[0]);
                if (sender.hasPermission(permission)) {
                    for (Kit kit : this.kits) {
                        if (kit.getPermission() == permission && !kit.DISABLED) {
                            kit.appendInventory((Player)sender);
                            return true;
                        }
                    }
                }
                break;
            case "kits.create":
                if (args.length == 2) {
                    this.create(args[0].toLowerCase(), args[1]);
                } else {
                    this.create(args[0].toLowerCase(), "");
                }
                break;
            case "kits.save":
                for (Kit kit : this.kits) {
                    kit.save(this.config);
                }
                return true;
        }
        return false;
    }
}
