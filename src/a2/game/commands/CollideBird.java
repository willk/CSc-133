package a2.game.commands;

import a2.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/21/15, 3:10 PM.
 */
public class CollideBird extends AbstractAction {
    private static CollideBird instance = null;
    private GameWorld gameWorld;

    private CollideBird() {
        super("Collide with Bird");
    }

    public static CollideBird getInstance() {
        if (instance == null)
            instance = new CollideBird();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.collideBird();
    }
}

