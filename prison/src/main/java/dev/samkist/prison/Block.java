package dev.samkist.prison;

/*
Custom block spawning can allow interceptor methods such as double drops.
 */

public class Block {
    Object obj; //Might be ItemStack or MobEntity
    double rate;
    public Block(Object obj, double rate) {
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
