package a3.game.objects;

import a3.GameWorldProxy;

import java.awt.*;

public class FuelCan extends Fixed implements IDrawable, ISelectable {
    private int size;
    private int capacity;
    private boolean firstDraw, selected;
    private Point cp;
    private Point tp;

    public FuelCan(GameWorldProxy gwp) {
        // When instantiating a fuel can make sure th value is between %5 and %30
        this.setColor(Color.red);
        this.setLocation();
        this.capacity = r.nextInt(25) + 10;
        this.size = 20;
        this.setWidth(size);
        this.setHeight(size);
        this.firstDraw = true;
        this.selected = false;
        setGWP(gwp);
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
            cp = centerObject(getX(), getY(), getSize(), getSize());
            tp = centerText(size, size, size, g);
            firstDraw = false;
        }

        if (!selected) {
            g.setColor(this.getColor());
            g.fillRect(round(cp.getX()), round(cp.getY()), round(getSize()), round(getSize()));

            g.setColor(invertColor(this.getColor()));
            g.drawString(Integer.toString(getCapacity()), round(tp.getX() + cp.getX()), round(tp.getY() + cp.getY()));
        } else {
            g.setColor(invertColor(this.getColor()));
            g.fillRect(round(cp.getX()), round(cp.getY()), round(getSize()), round(getSize()));

            g.setColor(this.getColor());
            g.drawString(Integer.toString(getCapacity()), round(tp.getX() + cp.getX()), round(tp.getY() + cp.getY()));
        }
    }

    @Override
    public void handleCollision(Collider obj) {
        if (obj instanceof Player) {
            delete();
            getGWP().addFuelCan();
        }
    }
}
