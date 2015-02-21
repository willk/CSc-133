package a1;

import java.util.Scanner;

/**
 * Created by willk on 2/15/15.
 */
public class Game {
    private GameWorld gw;

    public Game() {
        gw = new GameWorld();
        gw.initLayout();
        play();
    }

    private void play() {
        // Code to accept and execute user commands that operate on the GameWorld
        Scanner input = new Scanner (System.in);
        System.out.print("Input some text:");
        String l = input.nextLine();
    }
}
