package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/21/15, 3:33 PM.
 */
public class CmdCollideCar extends AbstractAction {
    private static CmdCollideCar instance;
    private GameWorldProxy gwp;

    private CmdCollideCar() {
        super("Collide with Car");
    }

    public static CmdCollideCar getInstance() {
        if (instance == null)
            instance = new CmdCollideCar();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Car collision logic.
        gwp.collideCar(0);
    }
}
