package a4.game.objects;

import a4.GameWorldProxy;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bird extends Movable implements IDrawable {
    private int size;
    private Beak beak;
    private Body body;
    private Wing[] wings;
    private double flapOffset, flapIncrement;

    public Bird(GameWorldProxy gwp) {
        this.setGWP(gwp);
        this.setSpeed(r.nextInt(25) + 10);
        this.setSize(r.nextInt(20) + 5);
        this.setHeading(r.nextInt(360));
        this.setLocation(new Point(r.nextInt((int)gwp.getRight()), r.nextInt((int)gwp.getTop())));
        this.setRotate(this.getHeading());
        this.setTranslate(getX(), getY());
        this.setColor(Color.blue);
        this.flapOffset = 0;
        this.flapIncrement = 0.05;

        beak = new Beak(Color.ORANGE, new Point(0, 4), new Point(3, 3), getHeading() + 180);
        body = new Body(this.getColor());
        wings = new Wing[2];

        Wing w0 = new Wing(Color.RED, new Point(0, 4), new Point(3, 3), getHeading() - 90);
        wings[0] = w0;

        Wing w1 = new Wing(Color.RED, new Point(0, 4), new Point(3, 3), getHeading() + 90);
        wings[1] = w1;
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

    public void flap() {
        flapOffset += flapIncrement;
        for (Wing w : wings) {
            w.translate(0, flapOffset);
        }
        if (Math.abs(flapOffset) >= .3) {
            flapIncrement *= -1;
        }
    }

    @Override
    public void move(double time) {
        if (getTranslate().getTranslateX() <= getGWP().getLeft()
                || getTranslate().getTranslateX() > getGWP().getRight()
                || getTranslate().getTranslateY() <= getGWP().getBottom()
                || getTranslate().getTranslateY() > getGWP().getTop())
            this.setHeading(this.getHeading() + 90);
        super.move(time);
        this.flap();
    }

    @Override
    public String toString() {
        return "Bird: " + super.toString() + ", size=" + this.getSize();
    }

    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform at = g2d.getTransform();


        g2d.transform(getTranslate());
        g2d.transform(getRotate());
        g2d.scale(1, -1);

        body.draw(g2d);
        beak.draw(g2d);
        for (Wing wing : wings) {
            wing.draw(g2d);
        }

        g2d.setTransform(at);
    }

    public class Body {
        private Color color;

        public Body(Color color) {
            this.color = color;
        }

        public void draw(Graphics2D g2d) {
            AffineTransform saveAT = g2d.getTransform();

            g2d.setColor(color);
            g2d.drawOval(-getWidth() / 2, -getHeight() / 2, getSize(), getSize());

            g2d.setTransform(saveAT);
        }
    }

    public class Wing {
        private Point top, left, right;
        private Color color;
        private AffineTransform t, s, r;

        public Wing(Color color, Point t, Point s, double d) {
            this.color = color;
            top = new Point(0, 2);
            left = new Point(-1, -2);
            right = new Point(1, -2);
            this.t = new AffineTransform();
            this.t.translate(t.getX(), t.getY());
            this.s = new AffineTransform();
            this.s.scale(s.getX(), s.getY());
            r = new AffineTransform();
            r.rotate(Math.toRadians(d));
        }


        public void draw(Graphics2D g2d) {
            AffineTransform at = g2d.getTransform();

            g2d.transform(r);
            g2d.transform(s);
            g2d.transform(t);

            g2d.setColor(color);
            int[] x = new int[] {top.x, left.x, right.y};
            int[] y = new int[] {top.y, left.y, right.x};
            g2d.fillPolygon(x, y, 3);

            g2d.setTransform(at);
        }

        public void translate(double dx, double dy) {
            t.translate(dx, dy);
        }
    }

    public class Beak {
        private Point top, left, right;
        private Color color;
        private AffineTransform t, s, r;

        public Beak(Color color, Point t, Point s, double d) {
            this.color = color;
            this.top = new Point(0,2);
            this.left = new Point(-1,-2);
            this.right = new Point(1, -2);
            this.t = new AffineTransform();
            this.s = new AffineTransform();
            this.r = new AffineTransform();
            this.t.translate(t.getX(), t.getY());
            this.s.scale(s.getX(), s.getY());
            this.r.rotate(Math.toRadians(d));
        }

        public void draw(Graphics2D g2d) {
            AffineTransform at = g2d.getTransform();

            g2d.transform(r);
            g2d.transform(s);
            g2d.transform(t);

            g2d.setColor(color);
            int[] x = new int[] {(int) top.getX(), (int) left.getX(), (int) right.getX()};
            int[] y = new int[] {(int) top.getY(), (int) left.getY(), (int) right.getY()};
            g2d.fillPolygon(x, y, 3);

            g2d.setTransform(at);
        }
    }
}
