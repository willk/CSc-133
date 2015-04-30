package a3.game.objects;

import java.awt.*;

public class FuelCan extends Fixed implements IDrawable {
    private int size;
    private int capacity;
    private boolean firstDraw;
    private Point cp;
    private Point tp;

    public FuelCan() {
        // When instantiating a fuel can make sure th value is between %5 and %30
        this.setColor(Color.red);
        this.setLocation();
        this.capacity = r.nextInt(25) + 10;
        this.size = 20;
        firstDraw = true;
    }

    public int getSize() {
        return size;
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
    public void draw(Graphics g) {
        if (firstDraw) {
            cp = centerObject(getX(), getY(), getSize(), getSize());
            tp = centerText(size, size, size, g);
            firstDraw = false;
        }

        g.setColor(this.getColor());
        g.fillRect(round(cp.getX()), round(cp.getY()), round(getSize()), round(getSize()));


        g.setColor(invertColor(this.getColor()));
        g.drawString(Integer.toString(getCapacity()), round(tp.getX() + cp.getX()), round(tp.getY() + cp.getY()));
    }
}
