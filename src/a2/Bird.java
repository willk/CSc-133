package a2;

import java.awt.*;

public class Bird extends Movable {
    int size;

    public Bird() {
        this.setSpeed(r.nextInt(15) + 10);
        this.setSize(r.nextInt(10) + 5);
        this.setLocation(new Point(r.nextInt(1000), r.nextInt(1000)));
        this.setColor(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    public void setColor() {
    }

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
        return "Bird: " + super.toString() + ", size=" + this.getSize();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
