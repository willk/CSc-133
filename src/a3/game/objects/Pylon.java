package a3.game.objects;

import a3.GameWorldProxy;

import java.awt.*;

public class Pylon extends Fixed implements IDrawable, ISelectable {
    private int radius;
    private int sequenceNumber;
    private boolean firstDraw;
    private Point cp, tp;
    private boolean selected;

    public Pylon(int number, GameWorldProxy gwp) {
        this.setLocation();
        init(number, gwp);
    }

    public Pylon(Point location, int number, GameWorldProxy gwp) {
        this.setLocation(location);
        init(number, gwp);

    }

    public void init(int number, GameWorldProxy gwp) {
        this.setRadius(50);
        this.setColor(Color.orange);
        this.setSequenceNumber(number);
        this.setGWP(gwp);
        this.firstDraw = true;
        this.selected = false;
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
    public void select(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean selected() {
        return selected;
    }

    @Override
    public boolean contains(Point p) {
        Rectangle r = new Rectangle(
                centerObject(
                        this.getX(),
                        this.getY(),
                        this.getWidth(),
                        this.getHeight()),
                new Dimension(this.getWidth(), this.getHeight()));

        return r.contains(p);

    }

    @Override
    public void draw(Graphics g) {
        if (firstDraw) {
            cp = new Point(getX() - round(getRadius() / 2), getY() - round(getRadius() / 2));
            tp = centerText(sequenceNumber, round(getRadius()), round(getRadius()), g);
            firstDraw = false;
        }

        if (!selected) {
            g.setColor(this.getColor());
            g.fillOval(round(cp.getX()), round(cp.getY()), round(getRadius()), round(getRadius()));

            g.setColor(invertColor(this.getColor()));
            g.drawString(Integer.toString(sequenceNumber), round(tp.getX() + cp.getX()), round(tp.getY() + cp.getY()));
        } else {
            g.setColor(invertColor(this.getColor()));
            g.fillOval(round(cp.getX()), round(cp.getY()), round(getRadius()), round(getRadius()));

            g.setColor(this.getColor());
            g.drawString(Integer.toString(sequenceNumber), round(tp.getX() + cp.getX()), round(tp.getY() + cp.getY()));
        }

    }
}
