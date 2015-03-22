package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/20/15, 7:16 PM.
 */
public class CmdAbout extends AbstractAction {
    private static CmdAbout instance;
    private GameWorldProxy gwp;

    private CmdAbout() {
        super("About");
    }

    public static CmdAbout getInstance() {
        if (instance == null)
            instance = new CmdAbout();
        return instance;
    }

    public void setTarget(GameWorldProxy gameWorldProxy) {
        this.gwp = gameWorldProxy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(
                null,
                "Race Car Game Extreme\n" +
                        "Version: " + gwp.getVersion() + "\n" +
                        "Created by: William Kinderman\n" +
                        "CSc 133, Dr. John Clevenger\n" +
                        "California State University, Sacramento",
                "Race Car Game Extreme About.",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
