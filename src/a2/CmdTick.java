package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:19 PM.
 */
public class CmdTick extends AbstractAction {
    private static CmdTick instance = null;
    private GameWorldProxy gwp;

    private CmdTick() {
        super("Tick Clock");
    }

    public static CmdTick getInstance() {
        if (instance == null)
            instance = new CmdTick();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.tick();
    }
}
