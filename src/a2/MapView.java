package a2;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * Created by William Kinderman on 3/14/15.
 */
public class MapView extends JPanel implements IObserver {
    public MapView() {
        setBackground(Color.black);
    }

    @Override
    public void update(IObservable o) {
        Iterator go = ((GameWorld) o).getGameCollection().iterator();
        while (go.hasNext())
            System.out.println(go.next().toString());
    }
}
