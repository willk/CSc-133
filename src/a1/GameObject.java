package a1;
import java.awt.Point;
import java.awt.Color;
import java.util.Random;

/**
 * Created by willk on 2/20/15.
 */

public abstract class GameObject {
    Random r = new Random(System.currentTimeMillis());

    private Point location;
    private Color color;

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point p) {
        this.location.setLocation(p);
    }

    public void setLocation(float x, float y) {
        this.location.setLocation(x, y);
    }

    public Color getColor() {
        return color;
    }

    public void setColor() {
        this.color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public void setColor(int r, int g, int b) {
        this.color = new Color(r, g, b);
    }

    @Override
    public String toString() {
        return ", location=" + this.getLocation() +
                ", color=" + this.getColor();
    }
}