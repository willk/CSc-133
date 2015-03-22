package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/21/15, 3:10 PM.
 */
public class CmdCollideBird extends AbstractAction {
    private static CmdCollideBird instance;
    private GameWorldProxy gwp;

    private CmdCollideBird() {
        super("Collide with Bird");
    }

    public static CmdCollideBird getInstance() {
        if (instance == null)
            instance = new CmdCollideBird();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.collideBird();
    }
}

