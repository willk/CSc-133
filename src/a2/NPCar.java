package a2;

import java.awt.*;

/**
 * Created by William Kinderman on 3/15/15.
 */
public class NPCar extends Car {
    private IStrategy strategy;
    private int uid;

    public NPCar(Point p, IStrategy s, int uid) {
        super(p);
        setStrategy(s);
        setUID(uid);
    }

    public IStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public int getUID() {
        return uid;
    }

    public void setUID(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "NPC Car " + getUID() + ": \n" +
                super.toString() +
                ", \nstrategy=" + getStrategy().toString() +
                ", uid=" + getUID();
    }

    @Override
    public void setDamageLevel(double damage) {
        super.setDamageLevel(damage / 2);
    }
}
