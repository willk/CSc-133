package a3.game.commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:18 PM.
 */
public class Accelerate extends AbstractAction {
    private static Accelerate instance = null;
    private GameWorld gameWorld;

    private Accelerate() {
        super("Accelerate");
    }

    public static Accelerate getInstance() {
        if (instance == null)
            instance = new Accelerate();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.accelerate();
    }
}
