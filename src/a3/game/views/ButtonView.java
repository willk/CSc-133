package a3.game.views;

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

    public ButtonView() {
        buttons = new ArrayList<JButton>();

        this.setLayout(new GridLayout(10, 1));
        this.setBorder(new TitledBorder("Game Commands:"));

        buttons.add(new JButton(CollideCar.getInstance()));
        buttons.add(new JButton(CollidePylon.getInstance()));
        buttons.add(new JButton(CollideBird.getInstance()));
        buttons.add(new JButton(PickUpFuelCan.getInstance()));
        buttons.add(new JButton(AddOilSlick.getInstance()));
        buttons.add(new JButton(EnterOilSlick.getInstance()));
        buttons.add(new JButton(ExitOilSlick.getInstance()));
        buttons.add(new JButton(ChangeStrategy.getInstance()));
        buttons.add(new JButton(Tick.getInstance()));
        buttons.add(new JButton(Quit.getInstance()));

        for (JButton button : buttons) {
            button.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), null);
            this.add(button);
        }
    }

    @Override
    public void update(IObservable o) {

    }
}
