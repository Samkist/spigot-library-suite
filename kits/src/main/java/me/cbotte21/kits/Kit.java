package me.cbotte21.kits;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Kit {
    public String name;
    public String description;
    public String permission;
    public boolean DISABLED = false;
    public int cooldown;
    private ArrayList<ItemStack> items;
    public Kit(String name, String description, String permission) { //CREATE
        this.name = name;
        this.description = description;
        this.permission = permission;
    }
    public Kit(String name, String permission) { //CREATE
        this.name = name;
        this.permission = permission;
    }
    public Kit(String name, ArrayList<ItemStack> items) { //LOAD
        this.name = name;
        this.items = items;
    }
    public void setInventory(Player p) {
        p.getInventory().clear();
        this.appendInventory(p);
    }
    public void appendInventory(Player p) {
        for (ItemStack item : this.items) {
            p.getInventory().addItem(item);
        }
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void addItem(ItemStack item) {
        this.items.add(item);
    }
    public void deleteItem(ItemStack item) {
        this.items.remove(item);
    }
    public String getPermission() {
        return this.permission;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public ArrayList<ItemStack> getItems() {
        return this.items;
    }
    public void save(FileConfiguration config) {
        //TODO: Save [NAME, ITEMS, PERMISSION] to config
        YMLConn.save(this, config);
    }
}
