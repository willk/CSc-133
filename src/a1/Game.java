package a1;

import java.util.Scanner;

public class Game {
    private GameWorld gw;
    private boolean valid;

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

        String command;
        String error = "Not a valid command.";

        System.out.printf("Please enter a command: ");
        Scanner input = new Scanner(System.in);

        do {
            setValid(false);
            command = input.nextLine();

            if (command.length() < 1 || command.length() > 3) System.out.println(error);
            if (command.length() > 2) {
                if (command.charAt(0) != 'p') System.out.println(error);
                if (command.charAt(0) == 'p' && getPylon(command) < 0) System.out.println(error);

            }
            setValid(true);

        } while (!isValid());


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
                gw.pylon(getPylon(command));
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

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    private int getPylon(String s) {
        char c;
        StringBuilder pylon = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            c = s.charAt(i);
            if (c == 'p') {}
            else if (c >= '0' && c <= '9')
                pylon.append(c);
            else {
                pylon.replace(0, 1, String.valueOf(-1));
                break;
            }
        }
        return Integer.parseInt(pylon.toString());
    }
}
