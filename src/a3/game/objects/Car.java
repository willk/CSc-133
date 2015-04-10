package a3.game.objects;

import a3.Point;

public class Car extends Movable implements ISteerable {
    private int pylon;

    private Point initialPoint;

    private double width;
    private double length;
    private double maxSpeed;
    private double maxFuel;
    private double fuel;
    private double direction;
    private double damage;
    private double maxDamage;

    public Car(Point location) {
        this.setSpeed(0);
        this.setHeading(90);
        this.setDamage(0);
        this.setMaxSpeed(100);
        this.setFuel(100);
        this.setMaxFuel(100);
        this.setMaxDamage(100);

        this.pylon = 1;
        this.setColor();

        this.setWidth(10);
        this.setLength(15);

        this.setLocation(location);
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getDirection() {
        return direction;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public int getPylon() {
        return pylon;
    }

    public void setPylon(int pylon) {
        if ((getPylon() + 1) == pylon)
            this.pylon = pylon;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public boolean hit(double damage) {
        // check to see if the car will exceed its maximum damage.
        if ((getDamage() + damage) < (getMaxDamage())) {
            this.setDamage(this.getDamage() + damage);
            this.setMaxSpeed(this.getMaxSpeed() - damage);
            return false;
        } else {
            return true;
        }

    }

    public double getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(double maxDamage) {
        this.maxDamage = maxDamage;
    }

    public void addFuel(double fuel) {
        if (this.getFuel() < this.getMaxFuel())
            if ((this.getFuel() + fuel) > this.getMaxFuel())
                this.setFuel(this.getMaxFuel());
            else
                this.setFuel(this.getFuel() + fuel);
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }

    public void reset() {
        // TODO: Reset to pylon 1
        this.setFuel(100);
        this.setDamage(0);
        this.setSpeed(0);
        this.setHeading(0);
        this.setMaxSpeed(100);
    }

    @Override
    public void move() {

        double theta = Math.toRadians(90 - getHeading());
        double dx = Math.cos(theta) * this.getSpeed();
        double dy = Math.sin(theta) * this.getSpeed();

        Point p = this.getLocation();
        p.translate((int) dx, (int) dy);
        this.setLocation(p);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", \npylon=" + this.getPylon() +
                ", direction=" + this.getDirection() +
                ", damage=" + this.getDamage() +
                ", fuel=" + this.getFuel() +
                ", maxSpeed=" + this.getMaxSpeed() +
                ", width=" + this.getWidth() +
                ", length=" + this.getLength();
    }

    @Override
    public void setSpeed(double speed) {
        if (this.getSpeed() >= 0)
            if ((this.getSpeed() < this.getMaxSpeed()))
                super.setSpeed(this.getSpeed() + speed);
        if (this.getSpeed() < 0)
            super.setSpeed(0);

    }

    @Override
    public void steerRight() {
        if (this.getDirection() < 40)
            this.setDirection(getDirection() + 5);
    }

    @Override
    public void steerLeft() {
        if (this.getDirection() > -40)
            this.setDirection(getDirection() - 5);
    }


}
