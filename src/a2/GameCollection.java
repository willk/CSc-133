package a2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by willk on 3/13/15.
 */
public class GameCollection implements ICollection {

    private int position;
    private ArrayList<GameObject> go;

    public void GameCollection() {
        position = 0;
        go = new ArrayList<GameObject>();
    }

    public GameObject get(int i) {
        return go.get(i);
    }

    @Override
    public int size() {
        return go.size();
    }

    @Override
    public void add(Object o) {
        go.add((GameObject) o);
    }

    @Override
    public Iterator<GameObject> iterator() {
        return new GameCollectionIterator();
    }

    private class GameCollectionIterator implements Iterator {
        private int index;

        public GameCollectionIterator() {
            index = -1;
        }

        @Override
        public boolean hasNext() {
            if (go.size() <= 0) return false;
            if (index == go.size() - 1) return false;
            return true;
        }

        @Override
        public Object next() {
            index++;
            return go.get(1);
        }
    }
}
