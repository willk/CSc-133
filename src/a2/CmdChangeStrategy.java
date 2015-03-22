package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:17 PM.
 */
public class CmdChangeStrategy extends AbstractAction {
    private static CmdChangeStrategy instance = null;
    private GameWorldProxy gwp;

    private CmdChangeStrategy() {
        super("Change Strategies");
    }

    public static CmdChangeStrategy getInstance() {
        if (instance == null)
            instance = new CmdChangeStrategy();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.changeStrategy();
    }
}
