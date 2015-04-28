package a3.game.views;

import a3.GameWorldProxy;
import a3.IObservable;
import a3.IObserver;
import a3.game.commands.*;
import a3.game.objects.IDrawable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by William Kinderman on 3/14/15, 6:45 PM, 6:45 PM.
 */
public class MapView extends JPanel implements IObserver {
    GameWorldProxy gwp;
    Dimension worldSize;

    public MapView(Dimension worldSize) {

        this.worldSize = worldSize;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(66, 11, 127)));
        this.setPreferredSize(worldSize);
        this.setMaximumSize(worldSize);
        this.setMinimumSize(worldSize);

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
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Object o : gwp.getGameCollection())
            if (o instanceof IDrawable)
                ((IDrawable) o).draw(g);
    }

    @Override
    public void update(IObservable gwp) {
        this.gwp = (GameWorldProxy) gwp;
        this.repaint();
    }
}
