package a4.game.objects;

/**
 * Created by William Kinderman on 4/27/15, 1:07 PM.
 */
public interface Collider {

    // apply appropriate detection algorithm
    boolean collidesWith(Collider otherObject);

    // apply appropriate response algorithm
    void handleCollision(Collider otherObject);
}
