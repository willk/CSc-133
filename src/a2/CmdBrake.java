package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:17 PM.
 */
public class CmdBrake extends AbstractAction {
    private static CmdBrake instance = null;
    private GameWorldProxy gwp;

    private CmdBrake() {
        super("Brake");
    }

    public static CmdBrake getInstance() {
        if (instance == null)
            instance = new CmdBrake();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.brake();
    }
}
