package a2.game.commands;

import a2.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/20/15, 7:16 PM.
 */
public class About extends AbstractAction {
    private static About instance = null;
    private GameWorld gameWorld;

    private About() {
        super("About");
    }

    public static About getInstance() {
        if (instance == null)
            instance = new About();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(
                null,
                "Race Car Game Extreme\n" +
                        "Version: " + gameWorld.getVersion() + "\n" +
                        "Created by: William Kinderman\n" +
                        "CSc 133, Dr. John Clevenger\n" +
                        "California State University, Sacramento",
                "Race Car Game Extreme About.",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
