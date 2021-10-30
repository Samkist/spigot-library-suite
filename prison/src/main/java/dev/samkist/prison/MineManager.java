package dev.samkist.prison;

import java.util.ArrayList;

public class MineManager {
    ArrayList<Mine> mines = new ArrayList<Mine>();
    public MineManager() {}
    public boolean create(String name, String permission, EventProbability[] blockSpawn, int minePos1, int minePos2, int zonePos1, int zonePos2, int legendLocation) {
        //IF Mine exist return false
        this.save();
        return true;
    }
    public Mine get(String name) {
        for (Mine mine : this.mines) {
            if (mine.name == name) return mine;
        }
        return null;
    }
    public boolean delete(Mine mine) {
        if (this.mines.contains(mine)) {
            this.mines.remove(mine);
            this.save();
            return true;
        }
        return false;
    }
    public void save() {

    }
}
