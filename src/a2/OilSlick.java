package a2;

import java.awt.*;

public class OilSlick extends Fixed {
    private double length;
    private double width;

    public OilSlick() {
        this.setColor(Color.black);
        this.setLocation();
        this.width = r.nextInt(45) + 10;
        this.length = r.nextInt(45) + 10;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "OilSlick: " + super.toString() +
                ", length=" + this.getLength() +
                ", width=" + this.getWidth();
    }
}
