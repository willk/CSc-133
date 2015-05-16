package a3.game.commands;

import a3.GameWorld;
import a3.game.views.MapView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 5/13/15, 8:44 PM.
 */

public class AddPylon extends AbstractAction {
    private static AddPylon instance = null;
    private GameWorld gameWorld;
    private MapView mv;

    private AddPylon() {
        super("Add Pylon");
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public static AddPylon getInstance() {
        if (instance == null)
            instance = new AddPylon();
        return instance;
    }

    public void setMV(MapView mv) {
        this.mv = mv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mv.togglePylon();
    }
}
