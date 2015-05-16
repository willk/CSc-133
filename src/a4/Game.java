package a4;

import a4.game.commands.*;
import a4.game.views.ButtonView;
import a4.game.views.MapView;
import a4.game.views.MenuBarView;
import a4.game.views.ScoreView;

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
        mbv = new MenuBarView();
        bv = new ButtonView();
        sv = new ScoreView();
        mv = new MapView(gwp);
        gw.initLayout();
        gw.addObserver(mbv);
        gw.addObserver(bv);
        gw.addObserver(sv);
        gw.addObserver(mv);

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
        AddFuelCan addFuelCan = AddFuelCan.getInstance();
        AddPylon addPylon = AddPylon.getInstance();
        Brake brake = Brake.getInstance();
        Delete delete = Delete.getInstance();
        EnterOilSlick enterOilSlick = EnterOilSlick.getInstance();
        ExitOilSlick exitOilSlick = ExitOilSlick.getInstance();
        NewColors newColors = NewColors.getInstance();
        Pause pause = Pause.getInstance();
        Quit quit = Quit.getInstance();
        SaveGame saveGame = SaveGame.getInstance();
        Sound sound = Sound.getInstance();
        Tick tick = Tick.getInstance();
        TurnLeft turnLeft = TurnLeft.getInstance();
        TurnRight turnRight = TurnRight.getInstance();

        // Pass GameWorld into the commands.

        about.setTarget(gw);
        accelerate.setTarget(gw);
        addFuelCan.setTarget(gw);
        addPylon.setTarget(gw);
        brake.setTarget(gw);
        delete.setTarget(gw);
        enterOilSlick.setTarget(gw);
        exitOilSlick.setTarget(gw);
        newColors.setTarget(gw);
        pause.setTarget(gw);
        quit.setTarget(gw);
        saveGame.setTarget(gw);
        sound.setTarget(gw);
        tick.setTarget(gw);
        turnLeft.setTarget(gw);
        turnRight.setTarget(gw);

        addFuelCan.setMV(mv);
        addPylon.setMV(mv);

        this.setVisible(true);
        this.requestFocus();
    }
}
