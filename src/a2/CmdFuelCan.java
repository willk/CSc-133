package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by William Kinderman on 3/20/15, 7:20 PM.
 */
public class CmdFuelCan extends AbstractAction {
    private static CmdFuelCan instance;
    private GameWorldProxy gwp;

    private CmdFuelCan() {
        super("Pick Up Fuel Can");
        super.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_F);
    }

    public static CmdFuelCan getInstance() {
        if (instance == null)
            instance = new CmdFuelCan();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.pickUpFuel();
    }
}
