package a1;

import java.util.Scanner;

/**
 * Created by willk on 2/15/15.
 */
public class GameWorld {

    public void initLayout() {
        // code here to create the initial game objects/layout.
    }

    public void accelerate() {
        /*
         * 'a'
         * Tell the game world to accelerate the player's car small amount.
         * Effects of acceleration are limited on damage, fuel, max speed.
         */
    }

    public void brake() {
        /*
         * 'b'
         * Reduce speed of player's car small amount.
         * If the car is in an oil slick, does nothing.
         */

    }

    public void left() {
        /*
         * 'l'
         * Change the steering direction of the car 5 degrees left.
         * Changes the direction of the car's steering wheel (not heading), does not immediately affect car's heading.
         */
    }

    public void right() {
        /*
         * 'r'
         * Change the steering direction of the car 5 degrees right.
         * Changes the direction of the car's steering wheel (not heading), does not immediately affect car's heading.
         */
    }

    public void oil_slick() {
        /*
         * 'o'
         * Tell the game world to add an oil slick randomly in the world.
         * Oil slicks have random size and location.
         */

    }

    public void collide() {
        /*
         * 'c'
         * Pretend that the player's car collided with some other car.
         * Tell the game world that this collision has occurred.
         * Colliding increases the player's damage level, if damage is 100% the car cannot move and player's lives
         *  decreases.
         */
    }

    public void pylon() {
        /*
         * 'pXX'
         * Pretend the player's car has driven over pylon XX.
         * Tell the game world that this collision has occurred.
         * The effect of driving over a pylon is to check to see whether the number XX is exactly one greater than the
         *  most recent pylon which the car collided with. If it is, then record in the car that the fact that the car
         *  has now reached the next sequential pylon.
         */
    }

    public void pickup_fuel() {
        /*
         * 'f'
         * pretend player's car has collided with a fuel can.
         * Tell the game world that this collision has occurred.
         * The effect of picking up a fuel can is to increase the car's fuel level by the size of the fuel can, remove
         *  the fuel can from the game and add a new fuel can with randomly-specified values back into the game.
         */
    }

    public void gum_up() {
        /*
         * 'g'
         * pretend that a bird has flown over the player's car.
         * Increase the damage to the car as described by description of Bird.
         */
    }

    public void enter_slick() {
        /*
         * 'e'
         * Tell game world player's car is in an oil slick.
         * The game world should set a flag in the player's car indicating it is in an oil slick.
         */
    }

    public void exit_slick() {
        /*
         * 'x'
         * Tell game world player's car is no longer in an oil slick.
         * The game world should clear the flag it created when car entered the slick.
         */
    }

    public void new_colors() {
        /*
         * 'n'
         * Tells the game world to generate random new colors for all objects that can change colors.
         */
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

    }

    public void map() {
        /*
         * 'm'
         * Tell the game world to output a map
         */

    }

    public void quit() {
        /*
         * 'q'
         * Call the method System.exit(0) to quit
         * Confirm exit prior to quiting.
         */
        System.out.printf("Quit? y/[n]: ");

        Scanner input = new Scanner(System.in);
        if (input.nextLine().toLowerCase().charAt(0) == 'y')
            System.exit(0);
    }


}
