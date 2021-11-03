package dev.samkist.prison;

/*
Custom block spawning can allow interceptor methods such as double drops.
 */

import org.bukkit.inventory.ItemStack;

public class MineBlock {
    Object obj; //Might be ItemStack or MobEntity
    double rate;
    public MineBlock(Object obj, double rate) {
        this.obj = obj;
        this.rate = rate;
    }
    public boolean eventOccuring() {
        return Math.random() > this.rate;
    }
    public Object getEvent() {
        return this.obj;
    }
}
