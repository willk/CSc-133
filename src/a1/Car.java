package a1;

import java.awt.*;

public class Car extends Movable implements ISteerable {

    private boolean traction;

    private int steeringDirection;
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
        this.setTraction(true);
        this.setMaxDamageLevel(100);

        this.pylon = 0;
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

    public int getPylon() {
        return pylon;
    }

    public void setPylon(int pylon) {
        this.pylon = pylon;
    }

    public boolean hasTraction() {
        return traction;
    }

    public double getMaxDamageLevel() {
        return maxDamageLevel;
    }

    public void setMaxDamageLevel(double maxDamageLevel) {
        this.maxDamageLevel = maxDamageLevel;
    }

    public void setTraction(boolean traction) {
        this.traction = traction;
    }

    public double getDamageLevel() {
        return damageLevel;
    }

    public void setDamageLevel(double damageLevel) {
        if (this.getDamageLevel() < this.getMaxDamageLevel()) {
            this.damageLevel += damageLevel;
        }
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
