package a3.game.objects;

import java.awt.*;
import java.util.HashMap;

public class Car extends Movable implements ISteerable {
    private int pylon;

    private double maxSpeed, maxFuel, fuel, direction, damage, maxDamage;
    private boolean traction;
    private HashMap<Collider, Boolean> collisionMap;
//    private ArrayList<Collider> collisionList;

    public Car(Point location) {
        this.setSpeed(0);
        this.setHeading(180);
        this.setDamage(0);
        this.setMaxSpeed(100);
        this.setFuel(100);
        this.setMaxFuel(100);
        this.setMaxDamage(100);

        this.pylon = 0;
        this.setColor();
        this.traction = true;

        this.setWidth(15);
        this.setHeight(10);

        this.setLocation(location);

//        this.collisionList = new ArrayList<Collider>();
        this.collisionMap = new HashMap<Collider, Boolean>();
    }

    public void toggleTraction() {
        this.traction = !traction;
    }

    public boolean hasTraction() {
        return traction;
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

    public void collide(Collider obj) {
        if (obj instanceof Bird) {
            this.hit(5);
        }
        if (obj instanceof FuelCan) {
            this.addFuel(((FuelCan) obj).getCapacity());
        }
        if (obj instanceof Car) {
            this.hit(10);
            if (this instanceof Player) {
                if (r.nextInt(100) < 20) {
                    getGWP().addOilSlick(((Car) obj).getLocation());
                }
            }
        }

        if (obj instanceof OilSlick) {

        }

        if (obj instanceof Pylon)
            if (((Pylon) obj).getSequenceNumber() == this.pylon + 1)
                pylon++;

        if (this.getDamage() > 100) delete();
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
                ", length=" + getHeight();
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

    @Override
    public void handleCollision(Collider obj) {
        collide(obj);
    }
}
