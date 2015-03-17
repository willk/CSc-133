package a2;

import javax.swing.*;
import java.util.Iterator;

/**
 * Created by William Kinderman on 3/14/15, 6:44 PM.
 */
public class ScoreView extends JPanel implements IObserver {
    private JLabel time, damage, lives, sound, pylon, fuel;

    public ScoreView() {
        drawScoreView();
    }

    private void drawScoreView() {
        time = new JLabel("Time: 0");
        lives = new JLabel("Lives: 3");
        pylon = new JLabel("Current Pylon: 1");
        fuel = new JLabel("Fuel Level: 100%");
        damage = new JLabel("Damage: 0");
        sound = new JLabel("Sound: Off");
        add(time);
        add(lives);
        add(pylon);
        add(fuel);
        add(damage);
        add(sound);
    }

    @Override
    public void update(IObservable o) {
        GameWorld gw = (GameWorld) o;
        GameCollection collection = gw.getGameCollection();
        time.setText("Time: " + gw.getTime());
        lives.setText("Lives: " + gw.getLives());
        Iterator i = collection.iterator();
        while (i.hasNext()) {
            GameObject go = (GameObject) i.next();
            if (go instanceof Player)
                pylon.setText("Current Pylon: " + ((Player) go).getPylon());
        }
        sound.setText("Sound: " + (gw.hasSound() ? "On" : "Off"));
    }
}
