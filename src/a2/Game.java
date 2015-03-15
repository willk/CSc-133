package a2;

import javax.swing.*;
import java.util.Scanner;

public class Game extends JPanel {
    private GameWorld gw;
    private ScoreView sv;
    private MapView mv;


    public Game() {
        gw = new GameWorld();
        sv = new ScoreView();
        mv = new MapView();
        gw.initLayout();
        gw.addObserver(sv);
        gw.addObserver(mv);
        play();
    }

    private void play() {
        // Code here to execute commands.
        while (true) {
            getCommand();
        }
    }

    private void getCommand() {
        /*
         * Valid commands:
         *  a: accelerate,
         *  b: break,
         *  l: left,
         *  r: right,
         *  o: create oil slick,
         *  c: collide,
         *  pXX: reach pylon XX,
         *  f: pickup fuel,
         *  g: gum up with bird,
         *  e: entered oil slick,
         *  x: exited oil slick,
         *  n: new colors,
         *  t: tick,
         *  d: display,
         *  m: map,
         *  q: quit game
         */

        String command;
        String error = "Not a valid command.";

        System.out.printf("Please enter a command: ");
        Scanner input = new Scanner(System.in);
        boolean valid;
        do {
            valid = true;
            command = input.nextLine();

            if (command.length() < 1 || command.length() > 3) valid = false;
            if (command.length() == 1 && command.charAt(0) == 'p')
                valid = false;
            if (command.length() > 1) {
                if (command.charAt(0) != 'p') valid = false;
                if (command.charAt(0) == 'p' && getPylonNumber(command) < 0)
                    valid = false;
            }
            if (!valid) {
                System.out.println(error);
                System.out.println("Please enter a command: ");
            }

        } while (!valid);


        switch (command.charAt(0)) {
            case 'a':
                gw.accelerate();
                break;
            case 'b':
                gw.brake();
                break;
            case 'l':
                gw.left();
                break;
            case 'r':
                gw.right();
                break;
            case 'o':
                gw.oilSlick();
                break;
            case 'c':
                gw.collide();
                break;
            case 'p':
                gw.pylon(getPylonNumber(command));
                break;
            case 'f':
                gw.pickupFuel();
                break;
            case 'g':
                gw.gumUp();
                break;
            case 'e':
                gw.enterSlick();
                break;
            case 'x':
                gw.exitSlick();
                break;
            case 'n':
                gw.newColors();
                break;
            case 't':
                gw.tick();
                break;
            case 'd':
                gw.display();
                break;
            case 'm':
                gw.map();
                break;
            case 'q':
                gw.quit();
                break;
            default:
                System.out.println(error);
                break;
        }
    }

    private int getPylonNumber(String s) {
        return Integer.parseInt(s.replaceAll("[\\D]", ""));
    }
}
