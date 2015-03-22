package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:18 PM.
 */
public class CmdAccelerate extends AbstractAction {
    private static CmdAccelerate instance;
    private GameWorldProxy gwp;

    private CmdAccelerate() {
        super("Accelerate");
    }

    public static CmdAccelerate getInstance() {
        if (instance == null)
            instance = new CmdAccelerate();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.accelerate();
    }
}
