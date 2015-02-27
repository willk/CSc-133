package a1;

import java.awt.*;
import java.util.Random;

public abstract class GameObject {
    Random r = new Random(System.nanoTime());

    private Point location;
    private Color color;

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point p) {
        this.location = p;
    }

    public void setLocation() {
        this.location = new Point(r.nextInt(1000), r.nextInt(1000));
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public void setColor() {
        this.color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    public void setColor(int r, int g, int b) {
        this.color = new Color(r, g, b);
    }

    @Override
    public String toString() {
        return "location=" + this.getLocation() +
                ", color=" + this.getColor();
    }
}