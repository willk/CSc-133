package a2;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener {
    private final String version = "2.0";
    private GameWorld gw;
    private ScoreView sv;
    private MapView mv;

    private JMenuItem jMNew, jMSave, jMAbout, jMQuit, jMSlick, jMFuel, jMColor;
    private JCheckBoxMenuItem jCMSound;


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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(bar);
        this.add(sv, BorderLayout.NORTH);
        this.add(mv, BorderLayout.CENTER);
        this.add(createWest(), BorderLayout.WEST);

        this.setVisible(true);

    }

    private JPanel createWest() {
        JPanel west = new JPanel();
        west.setLayout(new GridLayout(25, 1));
        west.setBorder(new TitledBorder("Game Commands"));
        west.add(new JButton("Collide with NPC"));
        west.add(new JButton("Collide with Pylon"));
        west.add(new JButton("Collide with Bird"));
        west.add(new JButton("Pick Up Fuel Can"));
        west.add(new JButton("Enter Oil Slick"));
        west.add(new JButton("Exit Oil Slick"));
        west.add(new JButton("Switch Strategy"));
        west.add(new JButton("Tick Clock"));
        west.add(new JButton("Quit"));

        return west;
    }


    private JMenuBar createMenu() {
        JMenuBar bar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        jMNew = new JMenuItem("New");
        fileMenu.add(jMNew);

        jCMSound = new JCheckBoxMenuItem("Sound");
        jCMSound.setMnemonic('S');
        fileMenu.add(jCMSound);

        jMSave = new JMenuItem("Save");
        fileMenu.add(jMSave);

        jMAbout = new JMenuItem("About");
        jMAbout.addActionListener(this);
        fileMenu.add(jMAbout);

        jMQuit = new JMenuItem("Quit");
        jMQuit.addActionListener(this);
        fileMenu.add(jMQuit);

        JMenu commands = new JMenu("Command");

        jMSlick = new JMenuItem("Add Oil Slick");
        jMSlick.setMnemonic('O');
        jMSlick.addActionListener(this);
        commands.add(jMSlick);

        jMFuel = new JMenuItem("Pick up Fuel Can");
        jMFuel.setMnemonic('F');
        jMFuel.addActionListener(this);
        commands.add(jMFuel);

        jMColor = new JMenuItem("New Colors");
        jMColor.setMnemonic('N');
        jMColor.addActionListener(this);
        commands.add(jMColor);

        bar.add(fileMenu);
        bar.add(commands);

        return bar;
    }

    private void showAbout(ActionEvent e) {
        final String info = "Race Car Game Extreme\n" +
                "Version" + version + "\n" +
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

        return dialog == 0 ? true : false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jMAbout)
            showAbout(e);
        if (e.getSource() == jMQuit)
            if (showQuitDialog(e))
                System.exit(0);
        if (e.getSource() == jMColor)
            gw.newColors();
        if (e.getSource() == jMFuel)
            gw.pickupFuel();

    }
}
