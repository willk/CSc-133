package a4.game.commands;

import a4.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/21/15, 3:19 PM.
 */
public class CollidePylon extends AbstractAction {
    private static CollidePylon instance = null;
    private GameWorld gameWorld;

    private CollidePylon() {
        super("Collide with Pylon");
    }

    public static CollidePylon getInstance() {
        if (instance == null)
            instance = new CollidePylon();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int pylon = gameWorld.getHighestPylon();
        String newPylon = Integer.toString(pylon);
        try {
            newPylon = JOptionPane.showInputDialog(
                    null,
                    "Enter a collidePylon number.",
                    pylon + 1
            );
        } catch (NumberFormatException error) {

        }
        gameWorld.collidePylon(Integer.parseInt(newPylon));
    }
}