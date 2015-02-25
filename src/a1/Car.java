package a1;

import java.awt.*;

/**
 * Created by willk on 2/20/15.
 */
public class Car extends Movable implements ISteerable {

    private boolean traction;

    private int steeringDirection;
    private int fuelLevel;
    private int maxSpeed;

    private double width;
    private double length;
    private double damageLevel;

    public Car(Point location) {
        this.setSpeed(0);
        this.setHeading(0);
        this.setDamageLevel(0);
        this.setMaxSpeed(100);
        this.setFuelLevel(100);
        this.setTraction(true);

        this.setColor();

        this.setWidth(10);
        this.setLength(15);

        this.setLocation(location);
    }

    @Override
    public void move() {
        if (this.hasTraction()) {

        }
    }

    public boolean hasTraction() {
        return traction;
    }

    public void setTraction(boolean traction) {
        this.traction = traction;
    }

    public double getDamageLevel() {
        return damageLevel;
    }

    public void setDamageLevel(int damageLevel) {
        this.damageLevel = damageLevel;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getSteeringDirection() {
        return steeringDirection;
    }

    public void setSteeringDirection(int steeringDirection) {
        this.steeringDirection = steeringDirection;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    @Override
    public void setSpeed(double speed) {
        if (this.getSpeed() < this.getMaxSpeed()) {
            super.setSpeed(this.getSpeed() + speed);
        }
    }

    @Override
    public void steerRight() {
        if (this.getSteeringDirection() < 40)
        this.setSteeringDirection(getSteeringDirection() + 5);

    }

    @Override
    public void steerLeft() {
        if (this.getSteeringDirection() < -40)
            this.setSteeringDirection(getSteeringDirection() - 5);
    }

    @Override
    public String toString() {
        return "Car: " + super.toString() +
                ", steeringDirection=" + this.getSteeringDirection() +
                ", damageLevel=" + this.getDamageLevel() +
                ", fuelLevel=" + this.getFuelLevel() +
                ", maxSpeed=" + this.getMaxSpeed() +
                ", width=" + this.getWidth() +
                ", length=" + this.getLength();
    }
}
