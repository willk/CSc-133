package a1;
import java.awt.Point;
import java.awt.Color;
/**
 * Created by willk on 2/20/15.
 */
public abstract class GameObject {
    private Point location;
    private Color color;

    public Point getLocation() {
        return location;
    }

    public void setLocation(float x, float y) {
        this.location.setLocation(x, y);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
