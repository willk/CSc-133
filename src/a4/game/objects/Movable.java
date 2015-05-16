package a4.game.objects;


import java.awt.*;

public abstract class Movable extends GameObject {

    private double speed;
    private double heading;


    public void move(double time) {
        double theta;
        if (getX() <= 0 || getX() > 1100 || getY() <= 0 || getY() > 720)
            this.setHeading(this.getHeading() + 90);
        theta = Math.toRadians(getHeading() + 180);

        double dx = Math.cos(theta) * (this.getSpeed());
        double dy = Math.sin(theta) * (this.getSpeed());

        Point p = this.getLocation();
        p.translate(round(dx / 5), round(dy / 5));
        this.setLocation(p);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = (heading % 360 + 360) % 360;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", speed=" + this.getSpeed() +
                ", heading=" + this.getHeading();
    }
}
