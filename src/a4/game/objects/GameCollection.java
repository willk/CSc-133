package a4.game.objects;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by William Kinderman on 3/13/15.
 */
public class GameCollection implements ICollection, Iterable<GameObject> {

    private ArrayList<GameObject> go;

    public GameCollection() {
        go = new ArrayList<GameObject>();
    }

    @Override
    public int size() {
        return go.size();
    }

    @Override
    public void add(GameObject o) {
        go.add(o);
    }

    @Override
    public boolean remove(GameObject o) {
        return go.remove(o);
    }

    @Override
    public Iterator<GameObject> iterator() {
        return new GameCollectionIterator();
    }

    private class GameCollectionIterator implements Iterator<GameObject> {
        private int index;

        public GameCollectionIterator() {
            index = -1;
        }

        @Override
        public boolean hasNext() {
            if (size() <= 0) return false;
            return index != size() - 1;
        }

        @Override
        public GameObject next() {
            index++;
            return go.get(index);
        }
    }
}
