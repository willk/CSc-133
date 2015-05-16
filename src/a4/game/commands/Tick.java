package a4.game.commands;

import a4.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:19 PM.
 */
public class Tick extends AbstractAction {
    private static Tick instance = null;
    private GameWorld gameWorld;

    private Tick() {
        super("Tick Clock");
    }

    public static Tick getInstance() {
        if (instance == null)
            instance = new Tick();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.tick();
    }
}
