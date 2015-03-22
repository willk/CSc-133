package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:19 PM.
 */
public class CmdQuit extends AbstractAction {
    private static CmdQuit instance;
    private GameWorldProxy gwp;

    private CmdQuit() {
        super("Quit");
        super.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_Q);
    }

    public static CmdQuit getInstance() {
        if (instance == null)
            instance = new CmdQuit();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int dialog = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to quit?",
                "Quit?",
                JOptionPane.YES_NO_OPTION
        );
        if (dialog == 0)
            gwp.quit();
    }
}