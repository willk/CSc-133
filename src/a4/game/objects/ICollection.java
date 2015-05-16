package a4.game.objects;

/**
 * Created by William Kinderman on 3/13/15.
 */
public interface ICollection {
    int size();

    void add(GameObject o);

    boolean remove(GameObject o);
}
