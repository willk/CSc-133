package a1;

public abstract class Movable extends GameObject {

    private double speed;
    private double heading;

    public void move() {}

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(float heading) {
        this.heading = heading;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", speed=" + this.getSpeed() +
                ", heading=" + this.getHeading();
    }
}
