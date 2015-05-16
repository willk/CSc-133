package a4.game.commands;

import a4.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/21/15, 3:33 PM.
 */
public class CollideCar extends AbstractAction {
    private static CollideCar instance = null;
    private GameWorld gameWorld;

    private CollideCar() {
        super("Collide with Car");
    }

    public static CollideCar getInstance() {
        if (instance == null)
            instance = new CollideCar();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Car collision logic.
        gameWorld.collideCar();
    }
}
