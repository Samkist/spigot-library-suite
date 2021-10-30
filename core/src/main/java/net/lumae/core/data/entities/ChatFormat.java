package net.lumae.core.data.entities;

import dev.morphia.annotations.Entity;

@Entity(value = "chatFormats", useDiscriminator = false)
public class ChatFormat extends Format {

    public ChatFormat() {

    }

    public ChatFormat(String name, String permission, String messageFormat, Integer priority) {
        super(name, permission, messageFormat, priority);
    }
}
