package net.lumae.broadcaster;

import net.kyori.adventure.text.Component;

import java.util.ArrayList;

public class Messages {
    long INTERVAL = 20L;
    int currentMsg = 0;
    ArrayList<Message> messages = new ArrayList<Message>();
    public void populate() {

    }
    public Component getMessage() {
        if (currentMsg > messages.size()) currentMsg = 0;
        return Component.text(this.messages.get(currentMsg).message);
    }
}
