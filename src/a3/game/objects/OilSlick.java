package a3.game.objects;

import java.awt.*;

public class OilSlick extends Fixed implements IDrawable {

    public OilSlick() {
        setLocation();
        init();
    }

    public OilSlick(Point p) {
        setLocation(p);
        init();
    }

    private void init() {
        setColor(Color.black);
        setHeight(r.nextInt(15) + 10);
        setWidth(r.nextInt(15) + 10);

    }

    @Override
    public String toString() {
        return "OilSlick: " + super.toString() +
                ", height=" + getHeight() +
                ", width=" + getWidth();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillOval(getX() - round(getHeight() / 2), getY() - round(getWidth() / 2), getWidth(), getHeight());
    }
}
