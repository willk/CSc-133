package a3;

import a3.game.objects.*;
import a3.game.strategies.DemolitionDerbyStrategy;
import a3.game.strategies.WillWinStrategy;

import java.util.ArrayList;
import java.util.Random;

public class GameWorld implements IGameWorld, IObservable {

    private final String version = "2.0";
    private int time;
    private int lives;
    private boolean sound;
    private Random r;
    private ArrayList<IObserver> observers;
    private Point[] pylonPoints;
    private GameCollection go;
    private Player player;
    private NPCar npCar1;
    private NPCar npCar2;
    private NPCar npCar3;

    public void initLayout() {

        time = 0;
        lives = 3;
        sound = false;

        r = new Random(System.nanoTime());

        go = new GameCollection();
        observers = new ArrayList<IObserver>();
        pylonPoints = new Point[]{
                new Point(125, 125),
                new Point(875, 125),
                new Point(875, 875),
                new Point(125, 875),
                new Point(500, 500)
        };

        // add pylons
        for (int i = 0; i < pylonPoints.length; i++) {
            addGameObject(new Pylon(pylonPoints[i], i));
        }

        // add cars
        addGameObject(player = new Player(pylonPoints[0]));
        addGameObject(npCar1 = new NPCar(new Point(125, 105), 0));
        addGameObject(npCar2 = new NPCar(new Point(125, 145), 1));
        addGameObject(npCar3 = new NPCar(new Point(125, 165), 2));
        npCar1.setStrategy(new WillWinStrategy(npCar1, new GameWorldProxy(this)));
        npCar2.setStrategy(new WillWinStrategy(npCar2, new GameWorldProxy(this)));
        npCar3.setStrategy(new DemolitionDerbyStrategy(npCar3, player));


        for (int i = 0; i < (r.nextInt(2) + 2); i++)
            addGameObject(new FuelCan());

        addGameObject(new OilSlick());
        addGameObject(new OilSlick());

        addGameObject(new Bird());
        addGameObject(new Bird());
    }

    public void setLives(int lives) {
        this.lives = lives;
        notifyObservers();
    }

    public void accelerate() {
        /*
         * 'a'
         * Tell the game world to accelerate the player's car small amount.
         * Effects of acceleration are limited on damage, fuel, max speed.
         */

        for (GameObject o : go)
            if (o instanceof Player)
                ((Player) o).setSpeed(5);

        notifyObservers();
    }

    public void addOilSlick() {
        /*
         * 'o'
         * Tell the game world to add an oil slick randomly in the world.
         * Oil slicks have random size and location.
         */

        go.add(new OilSlick());

        notifyObservers();
    }

    public void brake() {
        /*
         * 'b'
         * Reduce speed of player's car small amount.
         * If the car is in an oil slick, does nothing.
         */

        for (GameObject o : go)
            if (o instanceof Player)
                ((Player) o).setSpeed(-5);

        notifyObservers();
    }

    public void changeStrategy() {
        for (GameObject o : go) {
            if (o instanceof NPCar) {
                ((NPCar) o).setPylon((((NPCar) o).getPylon() + 1) % 5);
                if (((NPCar) o).getStrategy() instanceof WillWinStrategy)
                    ((NPCar) o).setStrategy(new DemolitionDerbyStrategy((NPCar) o, player));
                else
                    ((NPCar) o).setStrategy(new WillWinStrategy((NPCar) o, new GameWorldProxy(this)));
            }
        }

        notifyObservers();
    }

    public void left() {
        /*
         * 'l'
         * Change the steering direction of the car 5 degrees left.
         * Changes the direction of the car's steering wheel (not heading), does not immediately affect car's heading.
         */

        for (GameObject o : go) {
            if (o instanceof Player)
                ((Player) o).steerLeft();
        }

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

    public void right() {
        /*
         * 'r'
         * Change the steering direction of the car 5 degrees right.
         * Changes the direction of the car's steering wheel (not heading), does not immediately affect car's heading.
         */

        for (GameObject o : go)
            if (o instanceof Player)
                ((Player) o).steerRight();

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

        for (GameObject o : go)
            if (o instanceof Movable) {
                ((Movable) o).move();
                if (o instanceof Player)
                    if (((Player) o).getFuel() < 1)
                        lostLife();
                    else if (((Player) o).getMaxSpeed() == 0)
                        lostLife();
            }

        notifyObservers();

    }

    public void pickUpFuel() {
        /*
         * 'f'
         * pretend player's car has collided with a fuel can.
         * Tell the game world that this collision has occurred.
         * The effect of picking up a fuel can is to increase the Player's fuel level by the size of the fuel can, remove
         *  the fuel can from the game and add a new fuel can with randomly-specified values back into the game.
         */
        FuelCan f = null;

        for (GameObject o : go)
            if (o instanceof FuelCan) {
                f = (FuelCan) o;
                removeGameObject(o);
                break;
            }

        if (f != null)
            for (GameObject o : go)
                if (o instanceof Player) {
                    ((Player) o).addFuel(f.getSize());
                    break;
                }

        go.add(new FuelCan());

        notifyObservers();
    }

    public void newColors() {
        /*
         * 'n'
         * Tells the game world to generate random new colors for all objects that can change colors.
         */

        for (GameObject o : go)
            o.setColor();

        notifyObservers();
    }

    public void setSound(boolean sound) {
        this.sound = sound;
        notifyObservers();
    }

    public void enterSlick() {
        /*
         * 'e'
         * Tell game world player's car is in an oil slick.
         * The game world should set a flag in the player's car indicating it is in an oil slick.
         */

        for (GameObject o : go)
            if (o instanceof Player)
                ((Player) o).setTraction(false);

        notifyObservers();
    }

    public void exitSlick() {
        /*
         * 'x'
         * Tell game world player's car is no longer in an oil slick.
         * The game world should clear the flag it created when car entered the slick.
         */

        for (GameObject o : go)
            if (o instanceof Player)
                ((Player) o).setTraction(true);

        notifyObservers();
    }

    public void collideBird() {
        /*
         * 'g'
         * pretend that a bird has flown over the player's car.
         * Increase the damage to the car as described by description of Bird.
         */

        for (GameObject o : go)
            if (o instanceof Player)
                if (((Player) o).hit(5))
                    lostLife();

        notifyObservers();
    }

    public void collidePylon(int pylon) {
        /*
         * 'pXX'
         * Pretend the player's car has driven over collidePylon XX.
         * Tell the game world that this collision has occurred.
         * The effect of driving over a collidePylon is to check to see whether the number XX is exactly one greater than the
         *  most recent collidePylon which the car collided with. If it is, then record in the car that the fact that the car
         *  has now reached the next sequential collidePylon.
         */

        for (GameObject o : go)
            if (o instanceof Player)
                ((Player) o).setPylon(pylon);

        notifyObservers();
    }

    public void collideCar() {
        /*
         * 'c'
         * Pretend that the player's car collided with some other car.
         * Tell the game world that this collision has occurred.
         * Colliding increases the player's damage level, if damage is 100% the car cannot move and player's lives
         *  decreases.
         */

        // TODO: change the max speed of the colliding cars and ensure their speeds are not greater than the max speed.

        NPCar npCar = null;

        for (GameObject o : go)
            if (o instanceof Player)
                if (((Player) o).hit(10))
                    this.lostLife();

        for (GameObject o : go)
            if (o instanceof NPCar) {
                if (npCar == null) npCar = (NPCar) o;
                if (((NPCar) o).getDamage() <= npCar.getDamage()) npCar = (NPCar) o;
            }

        npCar.hit(10);

        notifyObservers();
    }

    private void lostLife() {
        //  TODO: When a life is lost the game should reset, all cars back to their start location and reset the damage for each.
        if (this.getLives() > 0) {
            this.setLives(this.getLives() - 1);

            for (GameObject o : go)
                if (o instanceof Player)
                    ((Player) o).reset();

            notifyObservers();

        } else if (this.getLives() <= 0) {
            System.out.println("Game Over!");
            this.quit();
        }
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public GameCollection getGameCollection() {
        return go;
    }

    @Override
    public void addGameObject(GameObject o) {
        go.add(o);
    }

    @Override
    public boolean removeGameObject(GameObject o) {
        return go.remove(o);
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public boolean getSound() {
        return sound;
    }

    @Override
    public int getHighestPylon() {

        for (GameObject o : go)
            if (o instanceof Player)
                return ((Player) o).getPylon();

        return -1;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public void addObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers)
            observer.update(new GameWorldProxy(this));
    }
}
