package a3.game.views;

import a3.GameWorldProxy;
import a3.IObservable;
import a3.IObserver;
import a3.game.commands.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by William Kinderman on 3/21/15, 2:16 PM.
 */
public class ButtonView extends JPanel implements IObserver {
    private ArrayList<JButton> buttons;
    private JButton pause, delete, addPylon, addFuel, quit;

    public ButtonView() {
        buttons = new ArrayList<JButton>();

        this.setLayout(new GridLayout(20, 1));
        this.setBorder(new TitledBorder("Game Commands:"));

        pause = new JButton(Pause.getInstance());
        delete = new JButton(Delete.getInstance());
        addPylon = new JButton(AddPylon.getInstance());
        addFuel = new JButton(AddFuelCan.getInstance());
        quit = new JButton(Quit.getInstance());

        delete.setEnabled(false);
        addPylon.setEnabled(false);
        addFuel.setEnabled(false);

        buttons.add(pause);
        buttons.add(delete);
        buttons.add(addPylon);
        buttons.add(addFuel);
        buttons.add(quit);

        for (JButton button : buttons) {
            button.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "none");
            this.add(button);
        }
    }

    @Override
    public void update(IObservable o) {
        if (((GameWorldProxy) o).paused()) {
            pause.setText("Play");
            delete.setEnabled(true);
            addPylon.setEnabled(true);
            addFuel.setEnabled(true);
        } else {
            pause.setText("Pause");
            delete.setEnabled(false);
            addPylon.setEnabled(false);
            addFuel.setEnabled(false);
        }
    }
}
