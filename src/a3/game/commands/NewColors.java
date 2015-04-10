package a3.game.commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by William Kinderman on 3/20/15, 7:25 PM.
 */
public class NewColors extends AbstractAction {
    private static NewColors instance = null;
    private GameWorld gameWorld;

    private NewColors() {
        super("New Colors");
        super.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
    }

    public static NewColors getInstance() {
        if (instance == null)
            instance = new NewColors();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.newColors();
    }
}
