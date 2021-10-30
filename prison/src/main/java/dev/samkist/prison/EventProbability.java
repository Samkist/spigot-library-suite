package dev.samkist.prison;

public class EventProbability {
    Object obj; //Might be ItemStack or MobEntity
    double rate;
    public EventProbability(Object obj, double rate) {
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
