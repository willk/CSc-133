package a4.game.objects;

import java.awt.*;

/**
 * Created by William Kinderman on 5/15/15, 12:42 PM.
 */
public interface ISelectable {
    void select(boolean selected);

    boolean selected();

    boolean contains(Point p);

    void draw(Graphics g);
}
