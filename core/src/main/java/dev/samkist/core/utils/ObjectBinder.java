package dev.samkist.core.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/*
    Makes saving data across multiple new CommandExecutor() so much easier.
 */

public class ObjectBinder {
    private class Record {
        Player p;
        Object obj;
        public Record(Player p, Object obj) {
            this.p = p;
            this.obj = obj;
        }
        public void setPlayer(Player p) {
            this.p = p;
        }
        public Player getPlayer() {
            return this.p;
        }
        public void setObject(Object obj) {
            this.obj = obj;
        }
        public Object getObject() {
            return this.obj;
        }
    }
    ArrayList<Record> memory = new ArrayList<Record>();
    public void save(Player p, Object obj) {
        for (Record record : this.memory) {
            if (record.getPlayer() == p) {
                record.setObject(obj);
                return;
            }
        }
        this.memory.add(new Record(p, obj));
    }
    public Object getObject(Player p) {
        for (Record record : this.memory) {
            if (record.getPlayer() == p) {
                return record.getObject();
            }
        }
        return null;
    }
}
