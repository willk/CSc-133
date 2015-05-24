package a4.game.objects;

import a4.GameWorldProxy;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class FuelCan extends Fixed implements IDrawable, ISelectable {
    private int size;
    private int capacity;
    private boolean selected;

    public FuelCan(GameWorldProxy gwp) {
        // When instantiating a fuel can make sure th value is between %5 and %30
        setGWP(gwp);
        this.setColor(Color.red);
        this.setLocation();
        this.capacity = r.nextInt(25) + 10;
        this.size = 20;
        this.setWidth(size);
        this.setHeight(size);
        this.selected = false;
        this.setTranslate(this.getLocation().getX(), this.getLocation().getY());
    }


    public int getSize() {
        return size;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "FuelCan: " + super.toString() +
                ", size=" + this.getSize() +
                ", capacity=" + this.getCapacity();
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
                        round(this.getTranslate().getTranslateX()),
                        round(this.getTranslate().getTranslateY()),
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
            g2d.fillRect(-getWidth()/2, -getHeight()/2, getSize(), getSize());

            g2d.setColor(invertColor(this.getColor()));
            g2d.drawString(Integer.toString(getCapacity()), 0, 0);
        } else {
            g2d.setColor(invertColor(this.getColor()));
            g2d.fillRect(-getWidth()/2, -getHeight()/2, getSize(), getSize());

            g2d.setColor(this.getColor());
            g2d.drawString(Integer.toString(getCapacity()), 0, 0);
        }

        g2d.setTransform(at);
    }

    @Override
    public void handleCollision(Collider obj) {
        if (obj instanceof Player) {
            delete();
            getGWP().addFuelCan();
        }
    }
}
