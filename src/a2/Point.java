package a2;

import java.awt.geom.Point2D;

/**
 * Created by William Kinderman on 3/23/15, 12:33 AM.
 * Add functionality to Java's built in Point class.
 */
public class Point extends Point2D.Double {

    public Point(double x, double y) {
        super(x, y);
    }

    public void translate(double dx, double dy) {
        super.x += dx;
        super.y += dy;
    }
}
