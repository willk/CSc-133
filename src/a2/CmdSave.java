package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/20/15, 7:09 PM.
 */
public class CmdSave extends AbstractAction {
    private static CmdSave instance;
    private GameWorldProxy gwp;

    private CmdSave() {
        super("Save");
    }

    public static CmdSave getInstance() {
        if (instance == null)
            instance = new CmdSave();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Future assignment.
    }
}
