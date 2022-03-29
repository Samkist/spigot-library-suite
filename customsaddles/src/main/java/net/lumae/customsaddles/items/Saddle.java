package net.lumae.customsaddles.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Saddle extends ItemStack {
    private EntityType entityType = EntityType.UNKNOWN;
    private boolean oneTimeUse = false;
    public Saddle(EntityType entityType) {
        this.entityType = entityType;
        this.setAmount(1);
        this.setType(Material.SADDLE);
        List<Component> lore = new ArrayList<Component>();
        lore.add(Component.text(":|: "+entityType.name()+" saddle :|:"));
        this.lore(lore);
    }
    public Saddle(EntityType entityType, boolean oneTimeUse) {
        this(entityType);
        this.oneTimeUse = oneTimeUse;
    }
    public EntityType getEntityType() {
        return this.entityType;
    }
    public boolean isEntityType(EntityType entityType) {
        return this.entityType == entityType;
    }
    public boolean isOneTimeUse() {
        return this.oneTimeUse;
    }
}
