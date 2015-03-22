package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by William Kinderman on 3/20/15, 7:13 PM.
 */
public class CmdSound extends AbstractAction {
    private static CmdSound instance;
    private GameWorldProxy gwp;

    private CmdSound() {
        super("Sound");
        super.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
    }

    public static CmdSound getInstance() {
        if (instance == null)
            instance = new CmdSound();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gwp.getSound())
            gwp.setSound(false);
        else
            gwp.setSound(true);
    }
}
