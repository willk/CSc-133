package a2.game.commands;

import a2.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:18 PM.
 */
public class TurnRight extends AbstractAction {
    private static TurnRight instance = null;
    private GameWorld gameWorld;

    private TurnRight() {
        super("Turn Right");
    }

    public static TurnRight getInstance() {
        if (instance == null)
            instance = new TurnRight();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.right();
    }
}
