package a2.game.objects;

import a2.Point;

/**
 * Created by William Kinderman on 3/15/15.
 */
public class Player extends Car {
    private boolean traction;

    public Player(Point p) {
        super(p);
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
    public void move() {

        if (this.hasTraction()) {
            this.setHeading(this.getHeading() + this.getDirection());
        }

        super.move();

        this.setFuel(this.getFuel() - 0.2);
    }
}


