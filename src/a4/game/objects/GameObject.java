package a4.game.objects;

import a4.GameWorldProxy;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Random;

public abstract class GameObject implements Collider {
    Random r = new Random(System.nanoTime());

    private GameWorldProxy gwp;
    private AffineTransform rotate = new AffineTransform();
    private AffineTransform scale = new AffineTransform();
    private AffineTransform translate = new AffineTransform();
    private Point location;
    private Color color;
    private int height, width;
    private HashMap<Collider, Boolean> collisionMap = new HashMap<Collider, Boolean>();

    public AffineTransform getRotate() {
        return rotate;
    }

    public void setRotate(double d) {
        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(d));
        this.rotate = at;
    }

    public AffineTransform getScale() {
        return scale;
    }

    public void setScale(AffineTransform scale) {
        this.scale = scale;
    }

    public AffineTransform getTranslate() {
        return translate;
    }

    public void setTranslate(double x, double y) {
        AffineTransform at = new AffineTransform();
        at.translate(x, y);
        this.translate = at;
    }

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
        this.location = new Point(r.nextInt((int)gwp.getRight()), r.nextInt((int)gwp.getTop()));
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

    public Point centerText(String s, int x, int y, Graphics2D g) {
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(s, g);

        return new Point((x - round(r.getWidth()) / 2), ((y - round(r.getHeight()) / 2)) + fm.getAscent());

    }

    public Point centerObject(int x, int y, int width, int length) {
        return new Point(x - round(width / 2), y - round(length / 2));
    }

    public Point centerText(int s, int x, int y, Graphics2D g) {
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
                        round(this.getTranslate().getTranslateX()),
                        round(this.getTranslate().getTranslateY()),
                        this.getWidth(),
                        this.getHeight()),
                new Dimension(getWidth(), getHeight()));

        Rectangle r2 = new Rectangle(
                centerObject(
                        round(((GameObject) obj).getTranslate().getTranslateX()),
                        round(((GameObject) obj).getTranslate().getTranslateY()),
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