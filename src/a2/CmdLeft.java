package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:18 PM.
 */
public class CmdLeft extends AbstractAction {
    private static CmdLeft instance = null;
    private GameWorldProxy gwp;

    private CmdLeft() {
        super("Turn Left");

    }

    public static CmdLeft getInstance() {
        if (instance == null)
            instance = new CmdLeft();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.left();
    }
}
