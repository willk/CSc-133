package a3.game.views;

import a3.GameWorldProxy;
import a3.IObservable;
import a3.IObserver;
import a3.game.objects.GameObject;
import a3.game.objects.Player;

import javax.swing.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by William Kinderman on 3/14/15, 6:44 PM.
 */
public class ScoreView extends JPanel implements IObserver {
    private AbstractList<JLabel> labels;
    private JLabel time, damage, lives, sound, pylon, fuel;

    public ScoreView() {
        labels = new ArrayList<JLabel>();

        time = new JLabel("Time: 0");
        lives = new JLabel("Lives: 3");
        pylon = new JLabel("Current Pylon: 1");
        fuel = new JLabel("Fuel Level: 100%");
        damage = new JLabel("Damage: 0");
        sound = new JLabel("Sound: Off");

        labels.addAll(Arrays.asList(time, lives, pylon, fuel, damage, sound));

        for (JLabel label : labels) {
            this.add(label);
        }
    }

    @Override
    public void update(IObservable o) {
        GameWorldProxy gwp = (GameWorldProxy) o;

        time.setText("Time: " + (int) (gwp.getTime() / 50));
        lives.setText("Lives: " + gwp.getLives());

        for (GameObject go : gwp.getGameCollection())
            if (go instanceof Player) {
                pylon.setText("Current Pylon: " + ((Player) go).getPylon());
                fuel.setText("Fuel Level: " + String.format("%.2f", ((Player) go).getFuel()) + "%");
                damage.setText("Damage: " + String.format("%.2f", ((Player) go).getDamage()) + "%");
            }

        sound.setText("Sound: " + (gwp.getSound() ? "On" : "Off"));
    }
}
