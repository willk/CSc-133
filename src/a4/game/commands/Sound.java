package a4.game.commands;

import a4.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by William Kinderman on 3/20/15, 7:13 PM.
 */
public class Sound extends AbstractAction {
    private static Sound instance = null;
    private GameWorld gameWorld;

    private Sound() {
        super("Sound");
        super.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
    }

    public static Sound getInstance() {
        if (instance == null)
            instance = new Sound();
        return instance;
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.toggleSound();
    }
}
