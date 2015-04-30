package a3.game.objects;

import java.awt.*;

public class Pylon extends Fixed implements IDrawable {
    private int radius;
    private int sequenceNumber;
    private boolean firstDraw;
    private Point cp, tp;

    public Pylon(int number) {
        this.setLocation();
        init(number);
    }

    public Pylon(Point location, int number) {
        this.setLocation(location);
        init(number);

    }

    public void init(int number) {
        this.setRadius(50);
        this.setColor(Color.orange);
        this.setSequenceNumber(number);
        this.firstDraw = true;
    }

    public void setColor() {
    }

    @Override
    public String toString() {
        return "Pylon: " + super.toString() +
                ", radius=" + this.getRadius() +
                ", sequenceNumber=" + this.getSequenceNumber();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        setHeight(radius);
        setWidth(radius);
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public void draw(Graphics g) {
        if (firstDraw) {
            cp = new Point(getX() - round(getRadius() / 2), getY() - round(getRadius() / 2));
            tp = centerText(sequenceNumber, round(getRadius()), round(getRadius()), g);
            firstDraw = false;
        }

        g.setColor(this.getColor());
        g.fillOval(round(cp.getX()), round(cp.getY()), round(getRadius()), round(getRadius()));


        g.setColor(invertColor(this.getColor()));
        g.drawString(Integer.toString(sequenceNumber), round(tp.getX() + cp.getX()), round(tp.getY() + cp.getY()));
    }
}