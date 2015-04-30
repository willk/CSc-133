package a3.game.objects;

import java.awt.*;

public class Bird extends Movable implements IDrawable {
    int size;

    public Bird() {
        this.setSpeed(r.nextInt(25) + 10);
        this.setSize(r.nextInt(10) + 5);
        this.setHeading(r.nextInt() % 360);
        this.setLocation(new Point(r.nextInt(_xMax), r.nextInt(_yMax)));
        this.setColor(Color.blue);
    }

    public void setColor() {}

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        setWidth(size);
        setHeight(size);
    }

    @Override
    public String toString() {
        return "Bird: " + super.toString() + ", size=" + this.getSize();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.drawOval(round(getX() - (getSize() / 2)), round(getY() - (getSize() / 2)), round(getSize()), round(getSize()));
    }
}
