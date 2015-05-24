package a4.game.objects;

import a4.GameWorldProxy;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class OilSlick extends Fixed implements IDrawable {
    public OilSlick(GameWorldProxy gwp) {
        this.setGWP(gwp);
        setLocation();
        init();
    }

    public OilSlick(GameWorldProxy gwp, Point p) {
        this.setGWP(gwp);
        setLocation(p);
        init();
    }

    private void init() {
        setColor(Color.black);
        setTranslate(getX(), getY());
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
    public void draw(Graphics2D g2d) {
        AffineTransform at = g2d.getTransform();

        g2d.transform(getTranslate());
        g2d.scale(1, -1);

        g2d.setColor(this.getColor());
        g2d.fillOval(-getWidth()/2, -getHeight()/2, getWidth(), getHeight());

        g2d.setTransform(at);
    }
}
