package a2.game.objects;

/**
 * Created by William Kinderman on 3/13/15.
 */
public interface ICollection {
    public int size();

    public void add(GameObject o);

    public boolean remove(GameObject o);
}
