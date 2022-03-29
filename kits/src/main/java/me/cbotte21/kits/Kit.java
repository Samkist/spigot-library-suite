package me.cbotte21.kits;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Kit {
    public String name;
    public String description = null;
    public String giveMessage = null;
    public String permission;
    public boolean DISABLED = false;
    public int cooldown;
    private ArrayList<ItemStack> items;
    public Kit(String name, String description, ArrayList<ItemStack> items, String permission) { //FULLY CUSTOMIZED KIT
        this.name = name;
        this.description = description;
        this.items = items;
        this.permission = permission;
    }
    public Kit(String name, ArrayList<ItemStack> items, String permission) { //KIT with no DESCRIPTION
        this.name = name;
        this.description = description;
        this.permission = permission;
    }
    public Kit(String name, String permission) { //KIT with no DESCRIPTION or ITEMS
        this.name = name;
        this.permission = permission;
    }
    public void setInventory(Player p) {
        p.getInventory().clear();
        this.appendInventory(p);
        if (this.giveMessage == null) {
            p.sendMessage("[Kits] You have been given kit "+this.name+".");
        } else {
            p.sendMessage(this.giveMessage);
        }
    }
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
    public int getCooldown() {
        return this.cooldown;
    }
    public void appendInventory(Player p) {
        for (ItemStack item : this.items) {
            if (p.getInventory().firstEmpty() == -1) { //Inventory full, drop item on floor
                p.getWorld().dropItem(p.getLocation(), item);
            } else {
                p.getInventory().addItem(item);
            }
        }
        if (this.giveMessage == null) {
            p.sendMessage("[Kits] You have redeemed kit "+this.name+".");
        } else {
            p.sendMessage(this.giveMessage);
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
    public void setGiveMessage(String giveMessage) {
        this.giveMessage = giveMessage;
    }
    public String getGiveMessage() {
        return this.giveMessage;
    }
    public void disable() {
        this.DISABLED = true;
    }
    public void enable() {
        this.DISABLED = false;
    }
    public boolean isDisabled() {
        return this.DISABLED;
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
}
