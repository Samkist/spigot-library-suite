package dev.samkist.core;

import org.bukkit.Location;

/*
Not sure how to create new directories w/ gradle. Placing here for now

TODO: Populate portals from local filestorage on
TODO: Add event listener if location == portal or steps on blocks or smth

Maybe use world edit to get 2 Positions and create a square from them?

 */

public class Portal {
    int id;
    Location initialLocation, finalLocation;
    String initialServer, finalServer;
    String block, permission;
    protected Portal(int id, Location initialLocation, String initialServer, Location finalLocation, String finalServer, String block, String permission) {
        this.id = id;
        this.initialLocation = initialLocation;
        this.initialServer = initialServer;
        this.finalLocation = finalLocation;
        this.finalServer = finalServer;
        this.block = block;
        this.permission = permission;
        this.build();
    }
    protected Portal(int id, Location initialLocation, String initialServer, Location finalLocation, String finalServer, String block) {
        this.id = id;
        this.initialLocation = initialLocation;
        this.initialServer = initialServer;
        this.finalLocation = finalLocation;
        this.finalServer = finalServer;
        this.build();
    }
    protected Portal() {}
    public void setInitialPosition(Location pos) {
        this.initialLocation = pos;
        this.build();
    }
    public void setFinalLocation(Location pos) {
        this.finalLocation = pos;
    }
    public void setBlock(String block) {
        this.block = block;
        this.build();
    }
    public int getId() {
        return this.id;
    }
    protected void build() {
        //TODO: Build in location if exists return
        //Temporarily build 5x9
    }
}
