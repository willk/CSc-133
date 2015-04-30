package a3.game.objects;

import a3.GameWorldProxy;
import a3.game.strategies.DemolitionDerbyStrategy;
import a3.game.strategies.IStrategy;
import a3.game.strategies.WillWinStrategy;

import java.awt.*;

/**
 * Created by William Kinderman on 3/15/15.
 */
public class NPCar extends Car implements IDrawable {
    private IStrategy strategy;
    private GameWorldProxy gwp;
    private int uid;

    public NPCar(Point p, int uid, GameWorldProxy gameWorldProxy) {
        super(p);
        this.uid = uid;
        this.gwp = gameWorldProxy;
        this.changeStrategy();
    }

    public IStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void changeStrategy() {
        if (getStrategy() instanceof DemolitionDerbyStrategy)
            setStrategy(new WillWinStrategy(this, gwp));
        else if (getStrategy() instanceof WillWinStrategy)
            setStrategy(new DemolitionDerbyStrategy(this, gwp));
        else if (r.nextInt(9) < 3)
            setStrategy(new DemolitionDerbyStrategy(this, gwp));
        else
            setStrategy(new WillWinStrategy(this, gwp));
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
    public void move(double time) {
        if ((time / 50) % 10 == 0)
            changeStrategy();

        if (time % 2 == 0) {
            setSpeed(strategy.apply() / 5);
            super.move(time);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.drawRect(getX() - round(getWidth() / 2), getY() - round(getHeight() / 2), getWidth(), getHeight());
    }
}
