package a4.game.objects;

import a4.GameWorldProxy;

import java.awt.*;
import java.awt.geom.AffineTransform;

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
        this.setTranslate(this.getLocation().getX(), this.getLocation().getY());
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
    public void draw(Graphics2D g2d) {
        AffineTransform at = g2d.getTransform();

        g2d.transform(getTranslate());
        g2d.scale(1, -1);


        if (!selected) {
            g2d.setColor(this.getColor());
            g2d.fillOval(-getWidth() / 2, -getHeight() / 2, getWidth(), getHeight());

            g2d.setColor(invertColor(this.getColor()));
            g2d.drawString(Integer.toString(sequenceNumber), 0, 0);
        } else {
            g2d.setColor(invertColor(this.getColor()));
            g2d.fillOval(-getWidth() / 2, -getHeight() / 2, round(getRadius()), round(getRadius()));

            g2d.setColor(this.getColor());
            g2d.drawString(Integer.toString(sequenceNumber), 0, 0);
        }

        g2d.setTransform(at);
    }
}
