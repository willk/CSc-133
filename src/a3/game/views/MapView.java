package a3.game.views;

import a3.GameWorldProxy;
import a3.IObservable;
import a3.IObserver;
import a3.game.commands.*;
import a3.game.objects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by William Kinderman on 3/14/15, 6:45 PM, 6:45 PM.
 */
public class MapView extends JPanel implements IObserver {
    private JTextArea map;

    public MapView() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(66, 11, 127)));

        // Create key maps.
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), KeyEvent.VK_UP);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, 0), KeyEvent.VK_O);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), KeyEvent.VK_DOWN);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), KeyEvent.VK_SPACE);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), KeyEvent.VK_LEFT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), KeyEvent.VK_Q);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), KeyEvent.VK_RIGHT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), KeyEvent.VK_T);

        // Populate the action map.
        ActionMap actionMap = this.getActionMap();
        actionMap.put(KeyEvent.VK_UP, Accelerate.getInstance());
        actionMap.put(KeyEvent.VK_O, AddOilSlick.getInstance());
        actionMap.put(KeyEvent.VK_DOWN, Brake.getInstance());
        actionMap.put(KeyEvent.VK_SPACE, ChangeStrategy.getInstance());
        actionMap.put(KeyEvent.VK_LEFT, TurnLeft.getInstance());
        actionMap.put(KeyEvent.VK_Q, Quit.getInstance());
        actionMap.put(KeyEvent.VK_RIGHT, TurnRight.getInstance());
        actionMap.put(KeyEvent.VK_T, Tick.getInstance());

        map = new JTextArea();
        map.setBackground(Color.white);
        map.setLineWrap(true);
        this.add(map, BorderLayout.CENTER);
    }

    @Override
    public void update(IObservable gwp) {
        map.setText(null);
        for (GameObject go : ((GameWorldProxy) gwp).getGameCollection()) {
            System.out.println(go.toString());
            map.append(go.toString() + "\n");
        }
    }
}
