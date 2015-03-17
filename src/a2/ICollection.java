package a2;

import java.util.Iterator;

/**
 * Created by William Kinderman on 3/13/15.
 */
public interface ICollection {
    public int size();

    public void add(GameObject o);

    public void remove(GameObject o);

    public Iterator iterator();
}
