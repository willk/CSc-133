package a4.game.commands;

import a4.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/20/15, 7:09 PM.
 */
public class SaveGame extends AbstractAction {
    private static SaveGame instance = null;
    private GameWorld gameWorld;

    private SaveGame() {
        super("Save");
    }

    public static SaveGame getInstance() {
        if (instance == null)
            instance = new SaveGame();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Future assignment.
    }
}
