package a3.game.commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by William Kinderman on 3/17/15, 3:19 PM.
 */
public class Quit extends AbstractAction {
    private static Quit instance = null;
    private GameWorld gameWorld;

    private Quit() {
        super("Quit");
        super.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_Q);
    }

    public static Quit getInstance() {
        if (instance == null)
            instance = new Quit();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int dialog = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to quit?",
                "Quit?",
                JOptionPane.YES_NO_OPTION
        );
        if (dialog == 0)
            gameWorld.quit();
    }
}