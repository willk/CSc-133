package a3.game.commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:17 PM.
 */
public class Brake extends AbstractAction {
    private static Brake instance = null;
    private GameWorld gameWorld;

    private Brake() {
        super("Brake");
    }

    public static Brake getInstance() {
        if (instance == null)
            instance = new Brake();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.brake();
    }
}
