package a4.game.objects;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bird extends Movable implements IDrawable {
    private int size;
    private AffineTransform translate, rotate, scale;
    private Body body;
    private Wing[] wings;
    private double flap, flapInc;

    public Bird() {
        this.setSpeed(r.nextInt(25) + 10);
        this.setSize(r.nextInt(20) + 10);
        this.setHeading(r.nextInt() % 360);
        this.setLocation(new Point(r.nextInt(_xMax), r.nextInt(_yMax)));
        this.setColor(Color.blue);

        this.body = new Body();
        body.scale(size / 2, size / 2);

        wings = new Wing[2];

        Wing wing = new Wing();
        wing.translate(0, 4);
        wing.scale(3, 3);
        wing.rotate(-90);
        wings[0] = wing;
        wing.setColor(Color.red);
        wing.translate(0, 4);
        wing.scale(3, 3);
        wing.rotate(90);
        wings[1] = wing;
        wing.setColor(Color.red);

        flap = 0;
        flapInc = .05;

        this.translate = new AffineTransform();
        this.rotate = new AffineTransform();
        this.scale = new AffineTransform();
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

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = g2d.getTransform();

        g2d.transform(rotate);
        g2d.transform(scale);
        g2d.transform(translate);

        body.draw(g2d);
        for (Wing w : wings) {
            w.draw(g2d);
        }

        g2d.setTransform(at);

    }

    @Override
    public void move(double time) {
        translate.translate(
                this.getSpeed() * Math.cos(Math.toRadians(this.getHeading())),
                this.getSpeed() * Math.sin(Math.toRadians(this.getHeading())));

        setLocation(new Point(round(this.translate.getTranslateX()), round(this.translate.getTranslateY())));

        flap += flapInc;
        for (Wing w : wings) {
            w.translate(0, flap);
        }
        if (Math.abs(flap) >= .3) {
            flapInc *= -1;
        }
    }

    public class Body {

        private Color color;
        private AffineTransform translate;
        private AffineTransform rotate;
        private AffineTransform scale;

        public Body() {
            this.color = Color.blue;
            this.translate = new AffineTransform();
            this.rotate = new AffineTransform();
            this.scale = new AffineTransform();
        }

        public void rotate(double d) {
            rotate.rotate(Math.toRadians(d));
        }

        public void translate(double dx, double dy) {
            translate.translate(dx, dy);
        }

        public void scale(double dx, double dy) {
            scale.scale(dx, dy);
        }

        public void draw(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform at = g2d.getTransform();

            g2d.transform(rotate);
            g2d.transform(scale);
            g2d.transform(translate);

            g2d.setColor(this.color);
            g2d.drawOval(-size / 2, -size / 2, round(getSize()), round(getSize()));

            g2d.setTransform(at);
        }
    }

    public class Wing {
        private Point top, bottomLeft, bottomRight;
        private Color color;
        private AffineTransform translate;
        private AffineTransform rotate;
        private AffineTransform scale;

        public Wing() {
            top = new Point(0, 2);
            bottomLeft = new Point(-1, -2);
            bottomRight = new Point(1, -2);
            translate = new AffineTransform();
            rotate = new AffineTransform();
            scale = new AffineTransform();
        }

        public void rotate(double degrees) {
            rotate.rotate(Math.toRadians(degrees));
        }

        public void scale(double sx, double sy) {
            scale.scale(sx, sy);
        }

        public void translate(double dx, double dy) {
            translate.translate(dx, dy);
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void draw(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);
            AffineTransform at = g2d.getTransform();

            g2d.transform(rotate);
            g2d.transform(scale);
            g2d.transform(translate);

            g2d.setColor(color);
            int[] polygonX = new int[]{top.x, bottomLeft.x, bottomRight.y};
            int[] polygonY = new int[]{top.y, bottomLeft.y, bottomRight.x};
            g2d.fillPolygon(polygonX, polygonY, 3);

            g2d.setTransform(at);
        }
    }
}

