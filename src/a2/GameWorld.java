package a2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameWorld implements IObservable {

    private int time;
    private int lives;
    private boolean sound;
    private ArrayList<IObserver> observers;
    private GameCollection go;

    public boolean hasSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
        notifyObservers();
    }

    public void initLayout() {

        time = 0;
        lives = 3;
        sound = false;

        Random r = new Random(System.nanoTime());

        go = new GameCollection();
        observers = new ArrayList<IObserver>();

        for (int i = 1; i < (r.nextInt(4) + 4); i++) {
            Point point = new Point(r.nextInt(1000), r.nextInt(1000));
            go.add(new Pylon(point, i));
            if (i == 1) {
                go.add(new Player(new Point(point)));
                // TODO: Code to add NPCars
            }
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
        Iterator iterator = go.iterator();
        while (iterator.hasNext())
            if (iterator.next() instanceof Player)
                ((Player) iterator).setSpeed(5);
    }

    public void brake() {
        /*
         * 'b'
         * Reduce speed of player's car small amount.
         * If the car is in an oil slick, does nothing.
         */

        Iterator iterator = go.iterator();
        while (iterator.hasNext())
            if (iterator instanceof Player)
                ((Player) iterator).setSpeed(-5);
    }

    public void left() {
        /*
         * 'l'
         * Change the steering direction of the car 5 degrees left.
         * Changes the direction of the car's steering wheel (not heading), does not immediately affect car's heading.
         */
        GameObject o;
        Iterator i = go.iterator();
        while (i.hasNext())
            if ((o = (GameObject) i.next()) instanceof Player)
                ((Player) o).steerLeft();
    }

    public void right() {
        /*
         * 'r'
         * Change the steering direction of the car 5 degrees right.
         * Changes the direction of the car's steering wheel (not heading), does not immediately affect car's heading.
         */
        GameObject o;
        Iterator i = go.iterator();
        while (i.hasNext())
            if ((o = (GameObject) i.next()) instanceof Player)
                ((Player) o).steerRight();
    }

    public void addOilSlick() {
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

        GameObject o;
        Iterator i = go.iterator();
        while (i.hasNext())
            if ((o = (GameObject) i.next()) instanceof Player) {
                if (((Player) o).hit(this.getCarHitCost()))
                    this.lostLife();
                break;
            }
    }

    public int getCarHitCost() {
        int carHitCost = 10;
        return carHitCost;
    }

    private void lostLife() {
        if (this.getLives() > 0) {
            this.setLives(this.getLives() - 1);

            GameObject o;
            Iterator i = go.iterator();

            while (i.hasNext())
                if ((o = (GameObject) i.next()) instanceof Player) {
                    ((Player) o).reset();
                    break;
                }
        } else if (this.getLives() <= 0) {
            System.out.println("Game Over!");
            System.exit(0);
        }
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
        notifyObservers();
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
        GameObject o;
        Iterator i = go.iterator();

        while (i.hasNext())
            if ((o = (GameObject) (i.next())) instanceof Player)
                ((Player) o).setPylon(sequence);
        notifyObservers();
    }

    public void pickupFuel() {
        /*
         * 'f'
         * pretend player's car has collided with a fuel can.
         * Tell the game world that this collision has occurred.
         * The effect of picking up a fuel can is to increase the Player's fuel level by the size of the fuel can, remove
         *  the fuel can from the game and add a new fuel can with randomly-specified values back into the game.
         */
        FuelCan f = null;
        GameObject o = null;
        Iterator iterator = go.iterator();
        while (iterator.hasNext())
            if ((o = (GameObject) iterator.next()) instanceof FuelCan) {
                f = (FuelCan) o;
                go.remove(o);
                break;
            }

        if (f != null) {
            iterator = go.iterator();
            while (iterator.hasNext())
                o = (GameObject) (iterator.next());
            if (o instanceof Player)
                ((Player) o).addFuel(((Player) o).getFuelLevel() + f.getSize());
        }

        go.add(new FuelCan());
        notifyObservers();
    }

    public void hitBird() {
        /*
         * 'g'
         * pretend that a bird has flown over the player's car.
         * Increase the damage to the car as described by description of Bird.
         */
        Iterator iterator = go.iterator();
        GameObject o;
        while (iterator.hasNext()) {
            if ((o = (GameObject) iterator.next()) instanceof Player) {
                if (((Player) o).hit(this.getBirdHitCost()))
                    this.lostLife();
                break;
            }
        }

        notifyObservers();
    }

    public int getBirdHitCost() {
        int birdHitCost = 5;
        return birdHitCost;
    }

    public void enterSlick() {
        /*
         * 'e'
         * Tell game world player's car is in an oil slick.
         * The game world should set a flag in the player's car indicating it is in an oil slick.
         */
        GameObject o;
        Iterator i = go.iterator();
        while (i.hasNext())
            if ((o = (GameObject) i.next()) instanceof Player)
                ((Player) o).setTraction(false);
        notifyObservers();
    }

    public void exitSlick() {
        /*
         * 'x'
         * Tell game world player's car is no longer in an oil slick.
         * The game world should clear the flag it created when car entered the slick.
         */
        GameObject o;
        Iterator i = go.iterator();
        while (i.hasNext())
            if ((o = (GameObject) i.next()) instanceof Player)
                ((Player) i).setTraction(true);
        notifyObservers();
    }

    public void newColors() {
        /*
         * 'n'
         * Tells the game world to generate random new colors for all objects that can change colors.
         */
        Iterator i = go.iterator();
        while (i.hasNext())
            ((GameObject) i.next()).setColor();
        notifyObservers();
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
        GameObject o;
        Iterator iterator = go.iterator();
        while (iterator.hasNext()) {
            o = (GameObject) iterator.next();
            if (o instanceof Movable)
                ((Movable) o).move();
            if (o instanceof Player)
                if (((Player) o).getFuelLevel() < 1)
                    this.lostLife();
                else if (((Player) o).getMaxSpeed() == 0)
                    this.lostLife();
        }
        notifyObservers();

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

        GameObject o;
        Player player = null;
        Iterator i = go.iterator();

        while (i.hasNext())
            if ((o = (GameObject) i.next()) instanceof Player) {
                player = (Player) o;
                break;
            }

        System.out.println("Lives left: " + this.lives);
        System.out.println("Clock: " + this.time);
        System.out.println("Highest Pylon: " + player.getPylon());
        System.out.printf("Player's fuel: %d, damage: %d\n", player.getFuelLevel(), (int) player.getDamageLevel());

    }

    public void map() {
        /*
         * 'm'
         * Tell the game world to output a map
         */
        notifyObservers();
    }

    public void quit() {
        /*
         * 'q'
         * Call the method System.exit(0) to quit
         * Confirm exit prior to quiting.
         */

        System.exit(0);
    }

    public GameCollection getGameCollection() {
        return go;
    }

    @Override
    public void addObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (IObserver o : observers)
            o.update(this);
    }

    public int getTime() {
        return time;
    }

    public void changeStrategies() {
    }

    public int getHighestPylon() {
        int pylon = -1;
        GameObject o;
        Iterator i = go.iterator();
        while (i.hasNext()) {
            if ((o = (GameObject) i.next()) instanceof Player)
                pylon = ((Player) o).getPylon();
        }
        return pylon;
    }
}
