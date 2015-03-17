package a2;

import java.awt.*;

public class Car extends Movable implements ISteerable {

    private boolean traction;

    private int steeringDirection;
    private int maxFuelLevel;
    private int fuelLevel;
    private int maxSpeed;
    private int pylon;

    private double width;
    private double length;
    private double damageLevel;
    private double maxDamageLevel;

    public Car(Point location) {
        this.setSpeed(0);
        this.setHeading(0);
        this.setDamageLevel(0);
        this.setMaxSpeed(100);
        this.setFuelLevel(100);
        this.setMaxFuelLevel(100);
        this.setTraction(true);
        this.setMaxDamageLevel(100);

        this.pylon = 1;
        this.setColor();

        this.setWidth(10);
        this.setLength(15);

        this.setLocation(location);
    }

    public void setTraction(boolean traction) {
        this.traction = traction;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public boolean hasTraction() {
        return traction;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void move() {
        if (this.hasTraction()) {
            this.setHeading(this.getHeading() + this.getSteeringDirection());
        }
        double theta = Math.toRadians(90 - getHeading());
        double dx = Math.cos(theta) * this.getSpeed();
        double dy = Math.sin(theta) * this.getSpeed();

        Point p = this.getLocation();
        p.translate((int) dx, (int) dy);
        this.setLocation(p);
        this.setFuelLevel(this.getFuelLevel() - 1);
    }

    public int getSteeringDirection() {
        return steeringDirection;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public void setSteeringDirection(int steeringDirection) {
        this.steeringDirection = steeringDirection;
    }

    @Override
    public String toString() {
        return "Car: " + super.toString() +
                ", pylon=" + this.getPylon() +
                ", traction=" + this.hasTraction() +
                ", steeringDirection=" + this.getSteeringDirection() +
                ", damageLevel=" + this.getDamageLevel() +
                ", fuelLevel=" + this.getFuelLevel() +
                ", maxSpeed=" + this.getMaxSpeed() +
                ", width=" + this.getWidth() +
                ", length=" + this.getLength();
    }

    public int getPylon() {
        return pylon;
    }

    public void setPylon(int pylon) {
        if ((getPylon() + 1) == pylon)
            this.pylon = pylon;
    }

    public double getDamageLevel() {
        return damageLevel;
    }

    public void setDamageLevel(double damage) {
        this.damageLevel = damage;
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

    @Override
    public void setSpeed(double speed) {
        if (this.getSpeed() >= 0)
            if ((this.getSpeed() < this.getMaxSpeed()) && this.hasTraction())
                super.setSpeed(this.getSpeed() + speed);
        if (this.getSpeed() < 0)
            super.setSpeed(0);

    }

    @Override
    public void steerRight() {
        if (this.getSteeringDirection() < 40)
            this.setSteeringDirection(getSteeringDirection() + 5);
    }

    @Override
    public void steerLeft() {
        if (this.getSteeringDirection() > -40)
            this.setSteeringDirection(getSteeringDirection() - 5);
    }

    public boolean hit(int damage) {
        // check to see if the car will exceed its maximum damage.
        if ((getDamageLevel() + damage) < (getMaxDamageLevel())) {
            this.setDamageLevel(this.getDamageLevel() + damage);
            this.setMaxSpeed(this.getMaxSpeed() - damage);
            return false;
        } else {
            return true;
        }

    }

    public double getMaxDamageLevel() {
        return maxDamageLevel;
    }

    public void setMaxDamageLevel(double maxDamageLevel) {
        this.maxDamageLevel = maxDamageLevel;
    }

    public void addFuel(int fuel) {
        if (this.getFuelLevel() < this.getMaxFuelLevel())
            if ((this.getFuelLevel() + fuel) > this.getMaxFuelLevel())
                this.setFuelLevel(this.getMaxFuelLevel());
            else
                this.setFuelLevel(this.getFuelLevel() + fuel);
    }

    public int getMaxFuelLevel() {
        return maxFuelLevel;
    }

    public void setMaxFuelLevel(int maxFuelLevel) {
        this.maxFuelLevel = maxFuelLevel;
    }

    public void reset() {
        this.setTraction(true);
        this.setFuelLevel(100);
        this.setDamageLevel(0);
        this.setSpeed(0);
        this.setHeading(0);
        this.setMaxSpeed(100);
    }


}
