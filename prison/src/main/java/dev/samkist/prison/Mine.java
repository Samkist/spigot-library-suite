package dev.samkist.prison;

import java.util.ArrayList;

public class Mine {
    String name, permission;
    boolean pvp;
    int minePos1, minePos2, zonePos1, zonePos2, legendLocation; //Will be 2 regions
    ArrayList<EventProbability> bossSpawn = new ArrayList<EventProbability>();
    ArrayList<EventProbability> blockSpawn = new ArrayList<EventProbability>();
    public Mine(String name, String permission, boolean pvp, ArrayList<EventProbability> blockSpawn, ArrayList<EventProbability> bossSpawn, int minePos1, int minePos2, int zonePos1, int zonePos2, int legendLocation) {
        this.name = name;
        this.permission = permission;
        this.pvp = pvp;
        this.blockSpawn = blockSpawn;
        this.bossSpawn = bossSpawn;
        this.minePos1 = minePos1;
        this.minePos2 = minePos2;
        this.zonePos1 = zonePos1;
        this.zonePos2 = zonePos2;
        this.legendLocation = legendLocation;
    }
    public void teleportOutPlayer() {
        //TODO: Teleport all players outside of mine. To closest axis point outside so they don't get grouped.
    }
    public void draw() {
        //TODO: Use FAWE To set the prison blocks.
    }
    public void createLegend() {
        //TODO: Holographic name at po
    }
}
