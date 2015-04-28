package a3.game.objects;

/**
 * Created by William Kinderman on 4/27/15, 1:07 PM.
 */
public interface ICollider {
    // apply appropriate detection algorithm
    boolean collidesWith(ICollider otherObject);

    // apply appropriate response algorithm
    void handleCollision(ICollider otherObject);
}
