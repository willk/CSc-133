package a3.game.commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:18 PM.
 */
public class AddOilSlick extends AbstractAction {
    private static AddOilSlick instance = null;
    private GameWorld gameWorld;

    private AddOilSlick() {
        super("Add Oil Slick");
        super.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O);
    }

    public static AddOilSlick getInstance() {
        if (instance == null)
            instance = new AddOilSlick();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.addOilSlick();
    }
}
