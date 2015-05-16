package a4;

import a4.game.objects.*;
import a4.game.strategies.DemolitionDerbyStrategy;
import a4.game.strategies.WillWinStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class GameWorld implements IGameWorld, IObservable, ActionListener {

    private final String version = "2.0";
    private double time;
    private Timer timer;
    private int lives;
    private Object temp;
    private boolean sound, paused;
    private Factory f;
    private Random r;
    private ArrayList<IObserver> observers;
    private GameCollection go;
    private Audio[] audio;
    private Audio theme, crash, slurp, death;
    private Stack<GameObject> graveyard;

    public void initLayout() {

        time = 0;
        lives = 3;
        sound = false;
        paused = false;

        r = new Random(System.nanoTime());
        graveyard = new Stack<GameObject>();

        go = new GameCollection();
        observers = new ArrayList<IObserver>();

        initAudio();

        f = new Factory(new GameWorldProxy(this), r);

        // add pylons
        for (int i = 0; i < 5; i++)
            f.mkPylon();

        // add cars
        f.mkPlayer();

        for (int i = 0; i < 3; i++) {
            f.mkNPCar();
        }

        for (int i = 0; i < (r.nextInt(2) + 2); i++)
            f.mkFuelCan();

        f.mkOilSlick();
        f.mkOilSlick();

        f.mkBird();
        f.mkBird();

        timer = new Timer(20, this);
        timer.start();

    }

    @Override
    public void addFuelCan() {
        f.mkFuelCan();
    }

    @Override
    public void addFuelCan(Point p, int capacity) {
        f.mkFuelCan(p, capacity);
    }

    @Override
    public boolean paused() {
        return paused;
    }

    @Override
    public void setTemp(Object o) {
        temp = o;
    }

    @Override
    public Object getTemp() {
        return temp;
    }

    @Override
    public void slurp() {
        slurp.play();
    }

    @Override
    public void crash() {
        crash.play();
    }

    @Override
    public void death() {
        death.play();
    }

    public void initAudio() {
        crash = new Audio(new String[]{"ouch1.wav", "ouch2.wav", "ouch3.wav"});
        death = new Audio(new String[]{"ohnoes.wav"});
        slurp = new Audio(new String[]{"slurp1.wav", "slurp2.wav", "slurp3.wav", "slurp4.wav", "slurp5.wav"});
        theme = new Audio(new String[]{"theme.wav"});
        if (getSound())
            theme.loop();
        audio = new Audio[]{death, crash, slurp, theme};
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
                    ((NPCar) o).setStrategy(new DemolitionDerbyStrategy((NPCar) o, new GameWorldProxy(this)));
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

    public void pause() {
        paused = !paused;
        if (paused) timer.stop();
        else timer.start();
        toggleSound();
        notifyObservers();
    }

    public void delete() {
        for (GameObject o : go) {
            if (o instanceof ISelectable) {
                if (((ISelectable) o).selected()) {
                    if (o instanceof Pylon) {
                        decrementPylons(((Pylon) o).getSequenceNumber());
                    }
                    o.delete();
                }
            }
        }
    }

    public void decrementPylons(int i) {
        for (GameObject o : go) {
            if (o instanceof Pylon)
                if (((Pylon) o).getSequenceNumber() > i)
                    ((Pylon) o).setSequenceNumber(((Pylon) o).getSequenceNumber() - 1);
        }
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
                ((Movable) o).move(getTime());
                if (o instanceof Player)
                    if (((Player) o).getFuel() < 1 || ((Player) o).getMaxSpeed() == 0) lostLife();
            }

        for (Collider o : go) {
            for (Collider o2 : go)
                if (o != o2)
                    if (o.collidesWith(o2)) {
                        o.handleCollision(o2);
                    }
        }


        for (; !graveyard.isEmpty(); go.remove(graveyard.pop())) {
        }

        notifyObservers();

    }

    public void collideFuel() {
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
                    ((Player) o).addFuel(f.getCapacity());
                    break;
                }

        go.add(new FuelCan(new GameWorldProxy(this)));

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

    public void toggleSound() {
        this.sound = !sound;

        if (!sound)
            for (Audio a : audio) a.stop();
        else if (!paused) theme.loop();

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
                ((Player) o).toggleTraction();

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
                ((Player) o).toggleTraction();

        notifyObservers();
    }

    public void collideBird() {
        /*
         * 'g'
         * pretend that a bird has flown over the player's car.
         * Increase the damage to the car as described by description of Bird.
         */

        // TODO: insert code to handle bird collisions.
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

        // TODO: insert code to call objects collision

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

        // TODO: insert code to call objects collision.

        if (sound)
            crash.play();

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
    public void addOilSlick(Point p) {
        f.mkOilSlick(p);
    }

    @Override
    public void addPylon(Point point, int pylon) {
        f.mkPylon(point, pylon);
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
    public double getTime() {
        return time;
    }

    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public void addToGraveyard(GameObject o) {
        graveyard.add(o);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        tick();
    }
}
