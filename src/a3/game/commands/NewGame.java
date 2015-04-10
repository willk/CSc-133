package a3.game.commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/20/15, 7:05 PM.
 */
public class NewGame extends AbstractAction {
    private static NewGame instance = null;
    private GameWorld gameWorld;

    private NewGame() {
        super("New");
    }

    public static NewGame getInstance() {
        if (instance == null)
            instance = new NewGame();
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
