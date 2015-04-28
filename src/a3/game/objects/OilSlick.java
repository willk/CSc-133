package a3.game.objects;

import java.awt.*;

public class OilSlick extends Fixed implements IDrawable {
    private double length;
    private double width;

    public OilSlick() {
        this.setColor(Color.black);
        this.setLocation();
        this.width = r.nextInt(15) + 10;
        this.length = r.nextInt(15) + 10;
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

    @Override
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillOval(round(getX() - (getLength() / 2)), round(getY() - (getLength() / 2)), round(getWidth()), round(getLength()));
    }
}
