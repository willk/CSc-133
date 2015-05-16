package a3.game.commands;

import a3.GameWorld;
import a3.game.views.MapView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by William Kinderman on 5/13/15, 7:56 PM.
 */
public class AddFuelCan extends AbstractAction {
    private static AddFuelCan instance = null;
    private GameWorld gameWorld;
    private MapView mv;

    private AddFuelCan() {
        super("Add Fuel Can");
    }

    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public static AddFuelCan getInstance() {
        if (instance == null)
            instance = new AddFuelCan();
        return instance;
    }

    public void setMV(MapView mv) {
        this.mv = mv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mv.toggleFuel();
    }
}
