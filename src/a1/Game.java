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

        System.out.printf("Please enter a command: ");
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();

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
                gw.oil_slick();
                break;
            case 'c':
                gw.collide();
                break;
            case 'p':
                getPylon(command);
                gw.pylon();
                break;
            case 'f':
                gw.pickup_fuel();
                break;
            case 'g':
                gw.gum_up();
                break;
            case 'e':
                gw.enter_slick();
                break;
            case 'x':
                gw.exit_slick();
                break;
            case 'n':
                gw.new_colors();
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
                break;
        }
    }

    private int getPylon(String s) {
        char c;
        StringBuilder pylon = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            c = s.charAt(i);
            if (c > '0' && c < '9')
                pylon.append(c);
        }
        return Integer.parseInt(pylon.toString());
    }
}
