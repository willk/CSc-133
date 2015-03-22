package a2;

import java.awt.*;

/**
 * Created by William Kinderman on 3/15/15.
 */
public class Player extends Car {
    public Player(Point p) {
        super(p);
    }

    @Override
    public String toString() {
        return "Player Car: \n" + super.toString();
    }
}


