package a2.game.views;

import a2.IObservable;
import a2.IObserver;
import a2.game.commands.*;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by William Kinderman on 3/20/15, 12:56 PM.
 */
public class MenuBarView extends JMenuBar implements IObserver {

    private JMenu file;
    private JMenu command;

    private ArrayList<AbstractButton> fileList;
    private ArrayList<AbstractButton> cmdList;

    public MenuBarView() {
        fileList = new ArrayList<AbstractButton>();
        cmdList = new ArrayList<AbstractButton>();

        file = new JMenu("File");
        command = new JMenu("Commands");


        fileList.add(new JMenuItem(NewGame.getInstance()));
        fileList.add(new JMenuItem(SaveGame.getInstance()));
        fileList.add(new JCheckBoxMenuItem(Sound.getInstance()));
        fileList.add(new JMenuItem(About.getInstance()));
        fileList.add(new JMenuItem(Quit.getInstance()));

        cmdList.add(new JMenuItem(AddOilSlick.getInstance()));
        cmdList.add(new JMenuItem(PickUpFuelCan.getInstance()));
        cmdList.add(new JMenuItem(NewColors.getInstance()));


        for (AbstractButton button : fileList) {
            if (button instanceof JMenuItem)
                file.add((JMenuItem) button);
        }

        for (AbstractButton button : cmdList) {
            command.add(button);
        }


        add(file);
        add(command);

    }

    @Override
    public void update(IObservable o) {

    }
}
