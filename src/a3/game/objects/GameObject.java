package a3.game.objects;

import a3.Point;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public abstract class GameObject implements ICollider {
    Random r = new Random(System.nanoTime());

    protected final int _xMax = 1000;
    protected final int _yMax = 720;

    private Point location;
    private Color color;
    private Boolean remove;
    private double height;
    private double width;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLocation() {
        this.location = new Point(r.nextInt(_xMax), r.nextInt(_yMax));
    }

    public void setLocation(Point p) {
        this.location = p;
    }

    public void setColor() {
        this.color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    public void setColor(int r, int g, int b) {
        this.color = new Color(r, g, b);
    }

    public Color invertColor(Color c) {
        return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
    }

    public Point getLocation() {
        return location;
    }

    public double getX() {
        return location.getX();
    }

    public double getY() {
        return location.getY();
    }

    public int round(double d) {
        return (int) (Math.round(d));
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Boolean remove() {
        return remove;
    }

    public void setRemove(Boolean remove) {
        this.remove = remove;
    }

    public Point centerText(String s, int x, int y, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(s, g);

        return new Point((x - r.getWidth()) / 2, ((y - r.getHeight()) / 2) + fm.getAscent());

    }

    public Point centerObject(double x, double y, double width, double length) {
        return new Point(x - (width / 2), y - (length / 2));
    }

    public Point centerText(int s, int x, int y, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(Integer.toString(s), g);

        return new Point((x - r.getWidth()) / 2, ((y - r.getHeight()) / 2) + fm.getAscent());

    }

    @Override
    public String toString() {
        return "location=" + this.getLocation() +
                ", color=" + this.getColor();
    }

    @Override
    public boolean collidesWith(ICollider otherObject) {
        Rectangle r1 = new Rectangle(centerObject(this.getX(), this.getY(), this.getWidth(), this.getHeight()));
        return false;
    }

    @Override
    public void handleCollision(ICollider otherObject) {

    }
}