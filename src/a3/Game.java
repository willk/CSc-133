package a3;

import a3.game.commands.*;
import a3.game.views.ButtonView;
import a3.game.views.MapView;
import a3.game.views.MenuBarView;
import a3.game.views.ScoreView;

import javax.swing.*;
import java.awt.*;


public class Game extends JFrame {
    private GameWorld gw;
    private MenuBarView mbv;
    private ButtonView bv;
    private ScoreView sv;
    private MapView mv;
    private Timer timer;
    private Dimension worldSize;

    public Game() {
        worldSize = new Dimension(1100, 720);

        gw = new GameWorld();
        mbv = new MenuBarView();
        bv = new ButtonView();
        sv = new ScoreView();
        mv = new MapView(worldSize);
        gw.initLayout();
        gw.addObserver(mbv);
        gw.addObserver(bv);
        gw.addObserver(sv);
        gw.addObserver(mv);

        timer = new Timer(20, Tick.getInstance());
        timer.start();

        this.setTitle("Race Car Game Extreme");
        this.setSize(1280, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setJMenuBar(mbv);
        this.add(sv, BorderLayout.NORTH);
        this.add(mv, BorderLayout.CENTER);
        this.add(bv, BorderLayout.WEST);


        // Create singleton instances of commands.

        About about = About.getInstance();
        Accelerate accelerate = Accelerate.getInstance();
        AddOilSlick addOilSlick = AddOilSlick.getInstance();
        Brake brake = Brake.getInstance();
        ChangeStrategy changeStrategy = ChangeStrategy.getInstance();
        CollideBird bird = CollideBird.getInstance();
        EnterOilSlick enterOilSlick = EnterOilSlick.getInstance();
        ExitOilSlick exitOilSlick = ExitOilSlick.getInstance();
        NewGame newGame = NewGame.getInstance();
        NewColors newColors = NewColors.getInstance();
        PickUpFuelCan pickUpFuelCan = PickUpFuelCan.getInstance();
        Quit quit = Quit.getInstance();
        SaveGame saveGame = SaveGame.getInstance();
        Sound sound = Sound.getInstance();
        Tick tick = Tick.getInstance();
        TurnLeft turnLeft = TurnLeft.getInstance();
        TurnRight turnRight = TurnRight.getInstance();

        // Pass GameWorld into the commands.

        about.setTarget(gw);
        accelerate.setTarget(gw);
        addOilSlick.setTarget(gw);
        brake.setTarget(gw);
        changeStrategy.setTarget(gw);
        bird.setTarget(gw);
        enterOilSlick.setTarget(gw);
        exitOilSlick.setTarget(gw);
        newGame.setTarget(gw);
        newColors.setTarget(gw);
        pickUpFuelCan.setTarget(gw);
        quit.setTarget(gw);
        saveGame.setTarget(gw);
        sound.setTarget(gw);
        tick.setTarget(gw);
        turnLeft.setTarget(gw);
        turnRight.setTarget(gw);

        this.setVisible(true);
        this.requestFocus();
    }
}
