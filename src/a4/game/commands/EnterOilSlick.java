package a4.game.commands;

import a4.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 3/21/15, 2:30 PM.
 */
public class EnterOilSlick extends AbstractAction {
    private static EnterOilSlick instance = null;
    private GameWorld gameWorld;

    private EnterOilSlick() {
        super("Enter Oil Slick");
    }

    public static EnterOilSlick getInstance() {
        if (instance == null)
            instance = new EnterOilSlick();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.enterSlick();
    }
}
