package a4.game.objects;

import a4.GameWorldProxy;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by William Kinderman on 3/15/15.
 */
public class Player extends Car implements IDrawable {
    /* Player:
     * There can only be one.
     */
    public Player(Point p, GameWorldProxy gameWorldProxy) {
        super(p);
        this.setColor();
        setGWP(gameWorldProxy);
    }

    @Override
    public String toString() {
        return "Player Car: \n" +
                super.toString() +
                ", traction=" + this.hasTraction();
    }

    @Override
    public void setSpeed(double speed) {
        if (this.hasTraction())
            super.setSpeed(speed);
    }

    @Override
    public void move(double time) {

        if (this.hasTraction()) {
            this.setHeading(this.getHeading() + this.getDirection());
        }

        super.move(time);

        this.setFuel(this.getFuel() - 0.05);
    }

    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform at = g2d.getTransform();

        g2d.transform(getTranslate());
        g2d.transform(getRotate());
        g2d.scale(1, -1);

        g2d.setColor(this.getColor());
        g2d.fillRect(-this.getWidth() / 2, -this.getHeight() / 2, this.getWidth(), this.getHeight());

        g2d.setTransform(at);
    }
}