package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/21/15, 3:19 PM.
 */
public class CmdCollidePylon extends AbstractAction {
    private static CmdCollidePylon instance;
    private GameWorldProxy gwp;

    private CmdCollidePylon() {
        super("Collide with Pylon");
    }

    public static CmdCollidePylon getInstance() {
        if (instance == null)
            instance = new CmdCollidePylon();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int pylon = gwp.getHighestPylon();
        String newPylon = Integer.toString(pylon);
        try {
            newPylon = JOptionPane.showInputDialog(
                    null,
                    "Enter a collidePylon number.",
                    pylon + 1
            );
        } catch (NumberFormatException error) {

        }
        gwp.collidePylon(Integer.parseInt(newPylon));
    }
}