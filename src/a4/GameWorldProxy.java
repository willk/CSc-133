package a4;

import a4.game.objects.GameCollection;
import a4.game.objects.GameObject;

import java.awt.*;

/**
 * Created by William Kinderman on 3/20/15, 6:37 PM.
 */
public class GameWorldProxy implements IGameWorld, IObservable {
    GameWorld gameWorld;

    public GameWorldProxy(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void addToGraveyard(GameObject o) {
        gameWorld.addToGraveyard(o);
    }

//    @Override
//    public Player getPlayer() {
//        return gameWorld.getPlayer();
//    }

    @Override
    public boolean paused() {
        return gameWorld.paused();
    }

    @Override
    public void setTemp(Object o) {
        gameWorld.setTemp(o);
    }

    @Override
    public Object getTemp() {
        return gameWorld.getTemp();
    }

    @Override
    public void slurp() {
        gameWorld.slurp();
    }

    @Override
    public void crash() {
        gameWorld.crash();
    }

    @Override
    public void death() {
        gameWorld.death();
    }

    @Override
    public int getXMin() {
        return gameWorld.getXMin();
    }

    @Override
    public int getXMax() {
        return gameWorld.getXMax();
    }

    @Override
    public int getYMin() {
        return gameWorld.getYMin();
    }

    @Override
    public int getYMax() {
        return gameWorld.getYMin();
    }

    @Override
    public GameCollection getGameCollection() {
        return gameWorld.getGameCollection();
    }

    @Override
    public void addGameObject(GameObject o) {
        gameWorld.addGameObject(o);
    }

    @Override
    public boolean removeGameObject(GameObject o) {
        return gameWorld.removeGameObject(o);
    }

    @Override
    public String getVersion() {
        return gameWorld.getVersion();
    }

    @Override
    public boolean getSound() {
        return gameWorld.getSound();
    }

    @Override
    public int getHighestPylon() {
        return gameWorld.getHighestPylon();
    }

    @Override
    public double getTime() {
        return gameWorld.getTime();
    }

    @Override
    public int getLives() {
        return gameWorld.getLives();
    }

    @Override
    public void addObserver(IObserver o) {
        gameWorld.addObserver(o);
    }

    @Override
    public void addFuelCan() {
        gameWorld.addFuelCan();
    }

    @Override
    public void addFuelCan(Point p, int capacity) {
        gameWorld.addFuelCan(p, capacity);
    }

    @Override
    public void addOilSlick(Point p) {
        gameWorld.addOilSlick(p);
    }

    @Override
    public void addPylon(Point point, int pylon) {
        gameWorld.addPylon(point, pylon);
    }

    @Override
    public void notifyObservers() {
        gameWorld.notifyObservers();
    }
}
