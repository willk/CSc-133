package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by William Kinderman on 3/20/15, 7:25 PM.
 */
public class CmdNewColors extends AbstractAction {
    private static CmdNewColors instance;
    private GameWorldProxy gwp;

    private CmdNewColors() {
        super("New Colors");
        super.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
    }

    public static CmdNewColors getInstance() {
        if (instance == null)
            instance = new CmdNewColors();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.newColors();
    }
}
