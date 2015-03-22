package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/21/15, 2:25 PM.
 */
public class CmdExitSlick extends AbstractAction {
    private static CmdExitSlick instance;
    private GameWorldProxy gwp;

    private CmdExitSlick() {
        super("Exit Oil Slick");
    }

    public static CmdExitSlick getInstance() {
        if (instance == null)
            instance = new CmdExitSlick();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.exitSlick();
    }
}
