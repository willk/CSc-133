package a1;

/**
 * Created by willk on 2/20/15.
 */
public abstract class Movable extends GameObject {
    private float heading;

    private void move(float x, float y) {
        this.setLocation(x, y);
    }
}
