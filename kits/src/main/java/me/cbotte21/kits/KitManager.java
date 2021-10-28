package me.cbotte21.kits;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

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
    public boolean create(String name, String description, String permission) {
        for (Kit kit : this.kits) { //Kit already exists
            if (kit.name == name) return false;
        }
        this.kits.add(new Kit(name, description));
        this.save();
        this.reload();
        return true;
    }
    public boolean create(String name, String permission) {
        return this.create(name, "", permission);
    }
    public Kit getKit(String name) {
        for (Kit kit : this.kits) {
            if (kit.name == name) return kit;
        }
        return null;
    }
    public ArrayList<String> getMyKits(Player p) {
        ArrayList<String> myKits = new ArrayList<String>();
        for (Kit kit : this.kits) {
            if (p.hasPermission(kit.getPermission())) {
                myKits.add(kit.name);
            }
            //TODO: if p.hasPermission kit.permission... myKits.add(kit.name)
        }
        return myKits;
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
    public boolean redeem(Player p, String kitName) {
        for (Kit kit : this.kits) {
            if (kit.name == kitName && p.hasPermission("kit.".concat(kitName))) {
                kit.appendInventory(p);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        switch (command.getPermission()) {
            case "kits.reload":
                this.reload();
                return true;
            case "kits.redeem":
                if (this.redeem((Player)sender, args[0].toLowerCase())) {
                    return true;
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
                this.save();
                return true;
        }
        return false;
    }
}
