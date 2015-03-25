package a2.game.commands;

import a2.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/21/15, 2:25 PM.
 */
public class ExitOilSlick extends AbstractAction {
    private static ExitOilSlick instance = null;
    private GameWorld gameWorld;

    private ExitOilSlick() {
        super("Exit Oil Slick");
    }

    public static ExitOilSlick getInstance() {
        if (instance == null)
            instance = new ExitOilSlick();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.exitSlick();
    }
}
