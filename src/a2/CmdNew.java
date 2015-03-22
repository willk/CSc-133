package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/20/15, 7:05 PM.
 */
public class CmdNew extends AbstractAction {
    private static CmdNew instance;
    private GameWorldProxy gwp;

    private CmdNew() {
        super("New");
    }

    public static CmdNew getInstance() {
        if (instance == null)
            instance = new CmdNew();
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
