package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:18 PM.
 */
public class CmdOilSlick extends AbstractAction {
    private static CmdOilSlick instance = null;
    private GameWorldProxy gwp;

    private CmdOilSlick() {
        super("Add Oil Slick");
        super.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O);
    }

    public static CmdOilSlick getInstance() {
        if (instance == null)
            instance = new CmdOilSlick();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.addOilSlick();
    }
}
