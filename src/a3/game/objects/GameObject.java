package a3.game.objects;

import a3.GameWorldProxy;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Random;

public abstract class GameObject implements Collider {
    Random r = new Random(System.nanoTime());

    protected final int _xMax = 1100;
    protected final int _yMax = 720;

    private Point location;
    private Color color;
    private int height;
    private int width;
    private GameWorldProxy gwp;
    private HashMap<Collider, Boolean> collisionMap = new HashMap<Collider, Boolean>();

    public GameWorldProxy getGWP() {
        return gwp;
    }

    public void setGWP(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    public void delete() {
        gwp.addToGraveyard(this);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
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

    public int getX() {
        return round(location.getX());
    }

    public int getY() {
        return round(location.getY());
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

    public Point centerText(String s, int x, int y, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(s, g);

        return new Point((x - round(r.getWidth()) / 2), ((y - round(r.getHeight()) / 2)) + fm.getAscent());

    }

    public Point centerObject(int x, int y, int width, int length) {
        return new Point(x - round(width / 2), y - round(length / 2));
    }

    public Point centerText(int s, int x, int y, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(Integer.toString(s), g);

        return new Point(round(x - r.getWidth()) / 2, (round(y - r.getHeight()) / 2) + fm.getAscent());

    }

    @Override
    public String toString() {
        return "location=" + this.getLocation() +
                ", color=" + this.getColor();
    }

    @Override
    public boolean collidesWith(Collider obj) {
        Rectangle r1 = new Rectangle(
                centerObject(
                        this.getX(),
                        this.getY(),
                        this.getWidth(),
                        this.getHeight()),
                new Dimension(getWidth(), getHeight()));

        Rectangle r2 = new Rectangle(
                centerObject(
                        ((GameObject) obj).getX(),
                        ((GameObject) obj).getY(),
                        ((GameObject) obj).getWidth(),
                        ((GameObject) obj).getHeight()),
                new Dimension(((GameObject) obj).getWidth(), ((GameObject) obj).getHeight()));

        // if we're colliding
        if (r1.intersects(r2) && !collisionMap.containsKey(obj)) collisionMap.put(obj, false);
            // if we're looking at an object that is contained within the collision list and
            // they are not intersecting any more, remove that object
        else if (collisionMap.containsKey(obj) && !r1.intersects(r2)) collisionMap.remove(obj);


        return r1.intersects(r2);
    }

    public HashMap<Collider, Boolean> getCollisionMap() {
        return collisionMap;
    }

    public void inHit(Collider obj) {
        collisionMap.put(obj, true);
    }

    @Override
    public void handleCollision(Collider obj) {}
}