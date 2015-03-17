package a2;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener {
    final String version = "2.0";
    private GameWorld gw;
    private ScoreView sv;
    private MapView mv;

    private JMenuItem fMNew, fMSave, fMAbout, fMQuit, fMSlick, fMFuel, fMColor;
    private JCheckBoxMenuItem fMSound;
    private JButton wPCollideNPC, wPCollidePylon, wPCollideBird, wPFuel, wPEnterSlick, wPExitSlick, wPStrat, wPTick, wPQuit;


    public Game() {
        gw = new GameWorld();
        sv = new ScoreView();
        mv = new MapView();
        gw.initLayout();
        gw.addObserver(sv);
        gw.addObserver(mv);
        drawWindow(createMenu());
    }

    private void drawWindow(JMenuBar bar) {
        setTitle("Race Car Game Extreme");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setJMenuBar(bar);
        this.add(sv, BorderLayout.NORTH);
        this.add(mv, BorderLayout.CENTER);
        this.add(createWest(), BorderLayout.WEST);

        this.setVisible(true);

    }

    private JMenuBar createMenu() {
        JMenuBar bar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        fMNew = new JMenuItem("New");
        fileMenu.add(fMNew);

        fMSound = new JCheckBoxMenuItem("Sound");
        fMSound.setMnemonic('S');
        fMSound.addActionListener(this);
        fileMenu.add(fMSound);

        fMSave = new JMenuItem("Save");
        fileMenu.add(fMSave);

        fMAbout = new JMenuItem("About");
        fMAbout.addActionListener(this);
        fileMenu.add(fMAbout);

        fMQuit = new JMenuItem("Quit");
        fMQuit.addActionListener(this);
        fileMenu.add(fMQuit);

        JMenu commands = new JMenu("Command");

        fMSlick = new JMenuItem("Add Oil Slick");
        fMSlick.setMnemonic('O');
        fMSlick.addActionListener(this);
        commands.add(fMSlick);

        fMFuel = new JMenuItem("Pick up Fuel Can");
        fMFuel.setMnemonic('F');
        fMFuel.addActionListener(this);
        commands.add(fMFuel);

        fMColor = new JMenuItem("New Colors");
        fMColor.setMnemonic('N');
        fMColor.addActionListener(this);
        commands.add(fMColor);

        bar.add(fileMenu);
        bar.add(commands);

        return bar;
    }

    private JPanel createWest() {
        JPanel west = new JPanel();
        west.setLayout(new GridLayout(25, 1));
        west.setBorder(new TitledBorder("Game Commands"));

        wPCollideNPC = new JButton("Collide with NPC");
        wPCollidePylon = new JButton("Collide with Pylon");
        wPCollideBird = new JButton("Collide with Bird");
        wPFuel = new JButton("Pick Up Fuel Can");
        wPEnterSlick = new JButton("Enter Oil Slick");
        wPExitSlick = new JButton("Exit Oil Slick");
        wPStrat = new JButton("Switch Strategy");
        wPTick = new JButton("Tick Clock");
        wPQuit = new JButton("Quit");

        wPCollideNPC.addActionListener(this);
        wPCollidePylon.addActionListener(this);
        wPCollideBird.addActionListener(this);
        wPFuel.addActionListener(this);
        wPEnterSlick.addActionListener(this);
        wPExitSlick.addActionListener(this);
        wPStrat.addActionListener(this);
        wPTick.addActionListener(this);
        wPQuit.addActionListener(this);

        west.add(wPCollideNPC);
        west.add(wPCollidePylon);
        west.add(wPCollideBird);
        west.add(wPFuel);
        west.add(wPEnterSlick);
        west.add(wPExitSlick);
        west.add(wPStrat);
        west.add(wPTick);
        west.add(wPQuit);

        return west;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fMSound) {
            gw.setSound(fMSound.getState());
        }
        if (e.getSource() == fMSlick)
            gw.addOilSlick();
        if (e.getSource() == fMAbout)
            showAbout(e);
        if (e.getSource() == fMQuit || e.getSource() == wPQuit)
            if (e.getSource() == wPQuit || showQuitDialog(e))
                gw.quit();
        if (e.getSource() == fMColor)
            gw.newColors();
        if (e.getSource() == fMFuel || e.getSource() == wPFuel)
            gw.pickupFuel();
        if (e.getSource() == wPCollideNPC)
            gw.collide();

        // Collide with a Pylon.
        if (e.getSource() == wPCollidePylon) {
            gw.pylon(showGetPylonDialog(e));
        }

        // Collide with a bird.
        if (e.getSource() == wPCollideBird)
            gw.hitBird();
        if (e.getSource() == wPEnterSlick)
            gw.enterSlick();
        if (e.getSource() == wPExitSlick)
            gw.exitSlick();
        if (e.getSource() == wPStrat)
            gw.changeStrategies();
        if (e.getSource() == wPTick)
            gw.tick();
    }

    private void showAbout(ActionEvent e) {
        final String info = "Race Car Game Extreme\n" +
                "Version: " + version + "\n" +
                "Created by: William Kinderman\n" +
                "CSc 133, Dr. John Clevenger\n" +
                "California State University, Sacramento";
        final String about = "Race Car Game Extreme About.";

        JOptionPane.showMessageDialog(
                (Component) e.getSource(),
                info,
                about,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private boolean showQuitDialog(ActionEvent e) {
        int dialog = JOptionPane.showConfirmDialog(
                (Component) e.getSource(),
                "Are you sure you want to quit?",
                "Quit?",
                JOptionPane.YES_NO_OPTION
        );

        return dialog == 0;
    }

    private int showGetPylonDialog(ActionEvent e) {
        // TODO: write code to print get pylon

        int pylon = gw.getHighestPylon();
        String newPylon = Integer.toString(pylon);
        try {
            newPylon = JOptionPane.showInputDialog(
                    (Component) e.getSource(),
                    "Enter a pylon number.",
                    pylon
            );
        } catch (NumberFormatException error) {

        }
        return Integer.parseInt(newPylon);
    }
}
