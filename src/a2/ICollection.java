package a2;

import java.util.Iterator;

/**
 * Created by willk on 3/13/15.
 */
public interface ICollection {
    public int size();

    public void add(Object o);

    public Iterator iterator();
}
