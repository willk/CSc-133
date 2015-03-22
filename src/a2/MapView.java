package a2;

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

        // Set KeyStrokes.
        KeyStroke up = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
        KeyStroke o = KeyStroke.getKeyStroke(KeyEvent.VK_O, 0);
        KeyStroke down = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
        KeyStroke space = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0);
        KeyStroke left = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
        KeyStroke q = KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0);
        KeyStroke right = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
        KeyStroke t = KeyStroke.getKeyStroke(KeyEvent.VK_T, 0);

        // Create key maps.
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(up, KeyEvent.VK_UP);
        inputMap.put(o, KeyEvent.VK_O);
        inputMap.put(down, KeyEvent.VK_DOWN);
        inputMap.put(space, KeyEvent.VK_SPACE);
        inputMap.put(left, KeyEvent.VK_LEFT);
        inputMap.put(q, KeyEvent.VK_Q);
        inputMap.put(right, KeyEvent.VK_RIGHT);
        inputMap.put(t, KeyEvent.VK_T);

        // Populate the action map.
        ActionMap actionMap = this.getActionMap();
        actionMap.put(KeyEvent.VK_UP, CmdAccelerate.getInstance());
        actionMap.put(KeyEvent.VK_O, CmdOilSlick.getInstance());
        actionMap.put(KeyEvent.VK_DOWN, CmdBrake.getInstance());
        actionMap.put(KeyEvent.VK_SPACE, CmdChangeStrategy.getInstance());
        actionMap.put(KeyEvent.VK_LEFT, CmdLeft.getInstance());
        actionMap.put(KeyEvent.VK_Q, CmdQuit.getInstance());
        actionMap.put(KeyEvent.VK_RIGHT, CmdRight.getInstance());
        actionMap.put(KeyEvent.VK_T, CmdTick.getInstance());

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
