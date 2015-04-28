package a3.game.objects;

import a3.Point;

import java.awt.*;

/**
 * Created by William Kinderman on 3/15/15.
 */
public class Player extends Car implements IDrawable {
    /* Player:
     * There can only be one.
     */
    private boolean traction;

    public Player(Point p) {
        super(p);
        this.setColor();
        this.traction = true;
    }

    public void setTraction(boolean traction) {
        this.traction = traction;
    }

    public boolean hasTraction() {
        return traction;
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
    public void reset() {
        this.setTraction(true);
        super.reset();
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
        g.setColor(this.getColor());
        g.fillRect(round(getX() - (getWidth() / 2)), round(getY() - (getLength() / 2)), round(getWidth()), round(getLength()));
    }
}


