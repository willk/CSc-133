package a1;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameWorld {

    private final int carHitCost = 10;
    private final int birdHitCost = 5;
    private int time;
    private int lives;
    private Random r;

    private ArrayList<GameObject> go;

    public void initLayout() {
        // code here to create the initial game objects/layout.

        time = 0;
        lives = 3;

        r = new Random(System.nanoTime());

        go = new ArrayList<GameObject>();

        for (int i = 1; i < (r.nextInt(4) + 4); i++) {
            Point point = new Point(r.nextInt(1000), r.nextInt(1000));
            go.add(new Pylon(point, i));
            if (i == 1) go.add(new Car(new Point(point)));
        }

        for (int i = 0; i < (r.nextInt(2) + 2); i++)
            go.add(new FuelCan());

        go.add(new OilSlick());
        go.add(new OilSlick());

        go.add(new Bird());
        go.add(new Bird());
    }

    public void accelerate() {
        /*
         * 'a'
         * Tell the game world to accelerate the player's car small amount.
         * Effects of acceleration are limited on damage, fuel, max speed.
         */

        for (GameObject o : go)
            if (o instanceof Car)
                ((Car) o).setSpeed(5);
    }

    public void brake() {
        /*
         * 'b'
         * Reduce speed of player's car small amount.
         * If the car is in an oil slick, does nothing.
         */

        for (GameObject o : go)
            if (o instanceof Car)
                ((Car) o).setSpeed(-5);

    }

    public void left() {
        /*
         * 'l'
         * Change the steering direction of the car 5 degrees left.
         * Changes the direction of the car's steering wheel (not heading), does not immediately affect car's heading.
         */

        for (GameObject o : go)
            if (o instanceof Car)
                ((Car) o).steerLeft();
    }

    public void right() {
        /*
         * 'r'
         * Change the steering direction of the car 5 degrees right.
         * Changes the direction of the car's steering wheel (not heading), does not immediately affect car's heading.
         */

        for (GameObject o : go)
            if (o instanceof Car)
                ((Car) o).steerRight();
    }

    public void oilSlick() {
        /*
         * 'o'
         * Tell the game world to add an oil slick randomly in the world.
         * Oil slicks have random size and location.
         */

        go.add(new OilSlick());
    }

    public void collide() {
        /*
         * 'c'
         * Pretend that the player's car collided with some other car.
         * Tell the game world that this collision has occurred.
         * Colliding increases the player's damage level, if damage is 100% the car cannot move and player's lives
         *  decreases.
         */

        for (GameObject o : go)
            if (o instanceof Car) {
                if (((Car) o).hit(this.getCarHitCost()))
                    this.lostLife();
                break;
            }
    }

    private void lostLife() {
        if (this.getLives() > 0) {
            this.setLives(this.getLives() - 1);

            for (GameObject o : go)
                if (o instanceof Car) {
                    ((Car) o).reset();
                    break;
                }
        } else if (this.getLives() <= 0) {
            System.out.println("Game Over!");
            System.exit(0);
        }
    }

    public void pylon(int sequence) {
        /*
         * 'pXX'
         * Pretend the player's car has driven over pylon XX.
         * Tell the game world that this collision has occurred.
         * The effect of driving over a pylon is to check to see whether the number XX is exactly one greater than the
         *  most recent pylon which the car collided with. If it is, then record in the car that the fact that the car
         *  has now reached the next sequential pylon.
         */
        for (GameObject o : go)
            if (o instanceof Car)
                ((Car) o).setPylon(sequence);
    }

    public void pickupFuel() {
        /*
         * 'f'
         * pretend player's car has collided with a fuel can.
         * Tell the game world that this collision has occurred.
         * The effect of picking up a fuel can is to increase the car's fuel level by the size of the fuel can, remove
         *  the fuel can from the game and add a new fuel can with randomly-specified values back into the game.
         */
        FuelCan f = null;
        for (GameObject o : go)
            if (o instanceof FuelCan) {
                f = (FuelCan) o;
                go.remove(o);
                break;
            }
        if (f != null)
            for (GameObject p : go)
                if (p instanceof Car)
                    ((Car) p).addFuel(((Car) p).getFuelLevel() + f.getSize());
    }

    public void gumUp() {
        /*
         * 'g'
         * pretend that a bird has flown over the player's car.
         * Increase the damage to the car as described by description of Bird.
         */

        for (GameObject o : go)
            if (o instanceof Car) {
                if (((Car) o).hit(this.getBirdHitCost()))
                    this.lostLife();
                break;
            }
    }

    public void enterSlick() {
        /*
         * 'e'
         * Tell game world player's car is in an oil slick.
         * The game world should set a flag in the player's car indicating it is in an oil slick.
         */
        for (GameObject o : go)
            if (o instanceof Car)
                ((Car) o).setTraction(false);
    }

    public void exitSlick() {
        /*
         * 'x'
         * Tell game world player's car is no longer in an oil slick.
         * The game world should clear the flag it created when car entered the slick.
         */
        for (GameObject o : go)
            if (o instanceof Car)
                ((Car) o).setTraction(true);
    }

    public void newColors() {
        /*
         * 'n'
         * Tells the game world to generate random new colors for all objects that can change colors.
         */

        for (GameObject o : go)
            o.setColor();
    }

    public void tick() {
        /*
         * 't'
         * Tell the game would that the "game clock" has ticked.
         * Clock ticks have the following effects:
         *  1. If the player's car is not in oil slick, then the car's heading should be incremented/decremented by the
         *   car's steeringDirection.
         *  2. The car's fuel lever is decreased by a small amount.
         *  3. All movable objects are told to update their position according to their current heading and speed.
         *  4. The game clock is incremented by 1.
         */
        time++;
        for (GameObject o : go) {
            if (o instanceof Movable)
                ((Movable) o).move();
            if (o instanceof Car)
                if (((Car) o).getFuelLevel() < 1)
                    this.lostLife();
                else if (((Car) o).getMaxSpeed() == 0)
                    this.lostLife();
        }

    }

    public void display() {
        /*
         * 'd'
         * Generate a display by outputting lines of text on the console describing the current game state values.
         * 1. Number of lives left
         * 2. Current clock value
         * 3. Highest sequential pylon value reached
         * 4. car's current fuel and damage level
         */

        Car car = null;

        for (GameObject o : go)
            if (o instanceof Car) {
                car = (Car) o;
                break;
            }

        System.out.println("Lives left: " + this.lives);
        System.out.println("Clock: " + this.time);
        System.out.println("Highest Pylon: " + car.getPylon());
        System.out.printf("Car's fuel: %d, damage: %d\n", car.getFuelLevel(), (int) car.getDamageLevel());

    }

    public void map() {
        /*
         * 'm'
         * Tell the game world to output a map
         */
        for (GameObject o : go)
            System.out.println(o.toString());

    }

    public void quit() {
        /*
         * 'q'
         * Call the method System.exit(0) to quit
         * Confirm exit prior to quiting.
         */

        System.out.printf("Quit? y/[n]: ");

        Scanner input = new Scanner(System.in);
        String command = input.nextLine().toLowerCase();
        while (true) {
            if (command.charAt(0) == 'y' || command.charAt(0) == 'n')
                break;
            System.out.println("Invalid command.");
            System.out.println("Quit? y/[n]");
            command = input.nextLine().toLowerCase();
        }
        if (command.charAt(0) == 'y') {
            System.out.println("Goodbye");
            System.exit(0);
        }
    }

    public int getCarHitCost() {
        return carHitCost;
    }

    public int getBirdHitCost() {
        return birdHitCost;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
