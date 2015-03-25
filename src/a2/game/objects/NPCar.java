package a2.game.objects;

import a2.Point;
import a2.game.strategies.IStrategy;

/**
 * Created by William Kinderman on 3/15/15.
 */
public class NPCar extends Car {
    private IStrategy strategy;
    private int uid;

    public NPCar(Point p, int uid) {
        super(p);
        setStrategy(strategy);
        this.uid = uid;
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

    @Override
    public String toString() {
        return "NPC Car " + getUID() + ": \n" +
                super.toString() +
                ", \nstrategy=" + getStrategy().toString() +
                ", uid=" + getUID();
    }

    @Override
    public void setDamage(double damage) {
        super.setDamage(damage / 2);
    }

    @Override
    public void move() {
        double speed = strategy.apply();
        super.setSpeed(speed);
    }
}
