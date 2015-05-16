package a3.game.commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 5/13/15, 7:56 PM.
 */
public class Pause extends AbstractAction {
    private static Pause instance = null;
    private GameWorld gameWorld;

    private Pause() {
        super("Pause");
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public static Pause getInstance() {
        if (instance == null)
            instance = new Pause();
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.pause();
    }
}
