package a2;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private GameWorld gw;
    private GameWorldProxy gwp;
    private MenuBarView mbv;
    private ButtonView bv;
    private ScoreView sv;
    private MapView mv;

    public Game() {
        gw = new GameWorld();
        gwp = new GameWorldProxy(gw);
        mbv = new MenuBarView(gwp);
        bv = new ButtonView(gwp);
        sv = new ScoreView();
        mv = new MapView();
        gw.initLayout();
        gw.addObserver(mbv);
        gw.addObserver(bv);
        gw.addObserver(sv);
        gw.addObserver(mv);

        setTitle("Race Car Game Extreme");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setJMenuBar(mbv);
        this.add(sv, BorderLayout.NORTH);
        this.add(mv, BorderLayout.CENTER);
        this.add(bv, BorderLayout.WEST);


        // Create singleton instances of commands.

        CmdAbout about = CmdAbout.getInstance();
        CmdAccelerate accelerate = CmdAccelerate.getInstance();
        CmdBrake brake = CmdBrake.getInstance();
        CmdChangeStrategy changeStrategy = CmdChangeStrategy.getInstance();
        CmdCollideBird bird = CmdCollideBird.getInstance();
        CmdCollideCar car = CmdCollideCar.getInstance();
        CmdCollidePylon pylon = CmdCollidePylon.getInstance();
        CmdEnterSlick enterSlick = CmdEnterSlick.getInstance();
        CmdExitSlick exitSlick = CmdExitSlick.getInstance();
        CmdFuelCan fuelCan = CmdFuelCan.getInstance();
        CmdLeft left = CmdLeft.getInstance();
        CmdNew newGame = CmdNew.getInstance();
        CmdNewColors newColors = CmdNewColors.getInstance();
        CmdOilSlick oilSlick = CmdOilSlick.getInstance();
        CmdQuit quit = CmdQuit.getInstance();
        CmdRight right = CmdRight.getInstance();
        CmdSave saveGame = CmdSave.getInstance();
        CmdSound sound = CmdSound.getInstance();
        CmdTick tick = CmdTick.getInstance();

        // Pass GameWorld into the commands.

        about.setTarget(gwp);
        accelerate.setTarget(gwp);
        brake.setTarget(gwp);
        changeStrategy.setTarget(gwp);
        bird.setTarget(gwp);
        car.setTarget(gwp);
        pylon.setTarget(gwp);
        enterSlick.setTarget(gwp);
        exitSlick.setTarget(gwp);
        fuelCan.setTarget(gwp);
        left.setTarget(gwp);
        newGame.setTarget(gwp);
        newColors.setTarget(gwp);
        oilSlick.setTarget(gwp);
        quit.setTarget(gwp);
        right.setTarget(gwp);
        saveGame.setTarget(gwp);
        sound.setTarget(gwp);
        tick.setTarget(gwp);

        this.setVisible(true);
        this.requestFocus();
    }
}
