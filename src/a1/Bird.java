package a1;

import java.awt.*;

public class Bird extends Movable {
    int size;

    public Bird() {
        this.setSize(r.nextInt(10) + 5);
        this.setLocation(new Point(r.nextInt(1000), r.nextInt(1000)));
        this.setColor(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(){}

    @Override
    public String toString() {
        return "Bird: " + super.toString() + ", size=" + this.getSize();
    }
}
