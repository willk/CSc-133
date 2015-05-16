package a4.game.commands;

import a4.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 5/13/15, 7:56 PM.
 */
public class Delete extends AbstractAction {
    private static Delete instance = null;
    private GameWorld gameWorld;

    private Delete() {
        super("Delete");
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public static Delete getInstance() {
        if (instance == null)
            instance = new Delete();
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.delete();
    }
}
