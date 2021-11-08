package dev.samkist.prison;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Boss {
    String name;
    Entity entity;
    double health, damageMultiplyer, spawnChance;
    ItemStack[] dropTable;
    public Boss(Player p, String name, EntityType entityType, double health, double damageMultiplyer, double spawnChance, ItemStack[] dropTable) {
        this.name = name;
        this.entity = p.getWorld().spawnEntity(p.getLocation(), entityType);
        this.health = health;
        this.damageMultiplyer = damageMultiplyer;
        this.spawnChance = spawnChance;
        this.dropTable = dropTable;
        entity.setCustomName(name);
        entity.setCustomNameVisible(true);
        entity.setGlowing(true);
    }
}
