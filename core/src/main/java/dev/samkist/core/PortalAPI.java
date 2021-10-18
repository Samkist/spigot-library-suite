package dev.samkist.core;

/*
On initialization
 */

import org.bukkit.Location;

import java.util.ArrayList;

public class PortalAPI {
    ArrayList<Portal> active_portals = new ArrayList<Portal>();
    public PortalAPI(ArrayList<Portal> p) { //TODO: Pass in config of all existing portals and push them
    }
    public PortalAPI() {}
    public void create(Location initialLocation, String initialServer, Location finalLocation, String finalServer, String block, String permission) { //TODO: Add to config
        this.active_portals.add(new Portal(this.active_portals.size(), initialLocation, initialServer, finalLocation, finalServer, block, permission));
    };
    public void create(Location initialLocation, String initialServer, Location finalLocation, String finalServer, String block) {
        this.active_portals.add(new Portal(this.active_portals.size(), initialLocation, initialServer, finalLocation, finalServer, block));
    }
    public void create() {
        this.active_portals.add(new Portal());
    }
    public void delete(Portal portal) { //TODO: Remove from config
        this.active_portals.remove(portal);
    }
    public ArrayList<Portal> getPortals() {
        return this.active_portals;
    }
    public Portal get(Portal portal) {
        for (Portal p : this.active_portals) {
            if(p == portal) return p;
        }
        return null;
    }
    public Portal get(int id) {
        for (Portal p : this.active_portals) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    public void reload() {
        for (Portal p : this.active_portals) {
            p.build();
        }
    }
}
