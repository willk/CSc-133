package a4.game.commands;

import a4.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:17 PM.
 */
public class ChangeStrategy extends AbstractAction {
    private static ChangeStrategy instance = null;
    private GameWorld gameWorld;

    private ChangeStrategy() {
        super("Change Strategies");
    }

    public static ChangeStrategy getInstance() {
        if (instance == null)
            instance = new ChangeStrategy();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.changeStrategy();
    }
}
