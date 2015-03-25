package a2.game.commands;

import a2.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:18 PM.
 */
public class TurnLeft extends AbstractAction {
    private static TurnLeft instance = null;
    private GameWorld gameWorld;

    private TurnLeft() {
        super("Turn Left");

    }

    public static TurnLeft getInstance() {
        if (instance == null)
            instance = new TurnLeft();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.left();
    }
}
