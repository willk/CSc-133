package a4.game.objects;

import a4.GameWorldProxy;
import a4.game.strategies.DemolitionDerbyStrategy;
import a4.game.strategies.IStrategy;
import a4.game.strategies.WillWinStrategy;

import java.awt.*;
import java.awt.geom.AffineTransform;

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
        this.setGWP(gwp);
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
            setSpeed(strategy.apply());
            super.move(time);
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform at = g2d.getTransform();

        g2d.transform(getTranslate());
        g2d.transform(getRotate());
        g2d.scale(1, -1);

        g2d.setColor(this.getColor());
        g2d.drawRect(-this.getWidth() / 2, -this.getHeight() / 2, this.getWidth(), this.getHeight());

        g2d.setTransform(at);
    }
}
