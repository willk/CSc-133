package a2;

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

    public ButtonView(GameWorldProxy gwp) {
        buttons = new ArrayList<JButton>();

        this.setLayout(new GridLayout(25, 1));
        this.setBorder(new TitledBorder("Game Commands:"));

        buttons.add(new JButton(CmdCollideCar.getInstance()));
        buttons.add(new JButton(CmdCollidePylon.getInstance()));
        buttons.add(new JButton(CmdCollideBird.getInstance()));
        buttons.add(new JButton(CmdFuelCan.getInstance()));
        buttons.add(new JButton(CmdOilSlick.getInstance()));
        buttons.add(new JButton(CmdEnterSlick.getInstance()));
        buttons.add(new JButton(CmdExitSlick.getInstance()));
        buttons.add(new JButton(CmdChangeStrategy.getInstance()));
        buttons.add(new JButton(CmdTick.getInstance()));
        buttons.add(new JButton(CmdQuit.getInstance()));

        for (JButton button : buttons) {
            button.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), null);
            this.add(button);
        }
    }

    @Override
    public void update(IObservable o) {

    }
}
