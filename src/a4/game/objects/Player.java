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
    private AffineTransform translate, rotate, scale;

    public Player(Point p, GameWorldProxy gameWorldProxy) {
        super(p);
        this.setColor();
        setGWP(gameWorldProxy);
        this.translate = new AffineTransform();
        this.rotate = new AffineTransform();
        this.scale = new AffineTransform();
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
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = g2d.getTransform();

        g2d.transform(rotate);
        g2d.transform(scale);
        g2d.transform(translate);

        g2d.setColor(this.getColor());
        g2d.fillRect(-round(getWidth() / 2), -round(getHeight() / 2), getWidth(), getHeight());


        g2d.transform(at);
    }
}


