package a2;

import java.util.ArrayList;

/**
 * Created by willk on 3/14/15.
 */
public class MapView implements IObserver {
    @Override
    public void update(IObservable o, Object arg) {
        ArrayList<GameObject> go = ((GameWorld) o).getGameCollection();
    }
}
