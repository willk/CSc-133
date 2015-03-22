package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/21/15, 2:30 PM.
 */
public class CmdEnterSlick extends AbstractAction {
    private static CmdEnterSlick instance;
    private GameWorldProxy gwp;

    private CmdEnterSlick() {
        super("Enter Oil Slick");
    }

    public static CmdEnterSlick getInstance() {
        if (instance == null)
            instance = new CmdEnterSlick();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.enterSlick();
    }
}
