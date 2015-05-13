package a3.game.objects;

import a3.GameWorldProxy;

import java.awt.*;

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
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillRect(getX() - round(getWidth() / 2), getY() - round(getHeight() / 2), getWidth(), getHeight());
    }
}


