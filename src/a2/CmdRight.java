package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:18 PM.
 */
public class CmdRight extends AbstractAction {
    private static CmdRight instance = null;
    private GameWorldProxy gwp;

    private CmdRight() {
        super("Turn Right");
    }

    public static CmdRight getInstance() {
        if (instance == null)
            instance = new CmdRight();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.right();
    }
}
