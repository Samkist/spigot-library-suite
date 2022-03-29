package net.lumae.core.data.entities;

import org.bukkit.inventory.ItemStack;

public class ChatFormat extends Format implements DatabaseObject {

    public ChatFormat() {
        ItemStack stack;
    }

    public ChatFormat(String name, String permission, String messageFormat, Integer priority) {
        super(name, permission, messageFormat, priority);
    }

    @Override
    public String getCollectionName() {
        return "chatFormats";
    }
}
