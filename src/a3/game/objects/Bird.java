package a3.game.objects;

import a3.Point;

public class Bird extends Movable {
    int size;

    public Bird() {
        this.setSpeed(r.nextInt(15) + 10);
        this.setSize(r.nextInt(10) + 5);
        this.setHeading(r.nextInt() % 360);
        this.setLocation(new Point(r.nextDouble() * 1000, r.nextDouble() * 1000));
        this.setColor(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    public void setColor() {}

    public void move() {
        double theta = Math.toRadians(90 - getHeading());
        double dx = Math.cos(theta) * this.getSpeed();
        double dy = Math.sin(theta) * this.getSpeed();

        Point p = this.getLocation();
        p.translate(dx, dy);
        this.setLocation(p);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Bird: " + super.toString() + ", size=" + this.getSize();
    }
}
