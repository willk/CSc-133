package a2;

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

    public MenuBarView(GameWorldProxy gwp) {
        fileList = new ArrayList<AbstractButton>();
        cmdList = new ArrayList<AbstractButton>();

        file = new JMenu("File");
        command = new JMenu("Commands");


        fileList.add(new JMenuItem(CmdNew.getInstance()));
        fileList.add(new JMenuItem(CmdSave.getInstance()));
        fileList.add(new JCheckBoxMenuItem(CmdSound.getInstance()));
        fileList.add(new JMenuItem(CmdAbout.getInstance()));
        fileList.add(new JMenuItem(CmdQuit.getInstance()));

        cmdList.add(new JMenuItem(CmdOilSlick.getInstance()));
        cmdList.add(new JMenuItem(CmdFuelCan.getInstance()));
        cmdList.add(new JMenuItem(CmdNewColors.getInstance()));


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
