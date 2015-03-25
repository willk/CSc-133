package a2.game.commands;

import a2.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by William Kinderman on 3/20/15, 7:20 PM.
 */
public class PickUpFuelCan extends AbstractAction {
    private static PickUpFuelCan instance = null;
    private GameWorld gameWorld;

    private PickUpFuelCan() {
        super("Pick Up Fuel Can");
        super.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_F);
    }

    public static PickUpFuelCan getInstance() {
        if (instance == null)
            instance = new PickUpFuelCan();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.pickUpFuel();
    }
}
