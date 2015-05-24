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
    public double getLeft() {
        return gameWorld.getLeft();
    }

    @Override
    public double getRight() {
        return gameWorld.getRight();
    }

    @Override
    public double getBottom() {
        return gameWorld.getBottom();
    }

    @Override
    public double getTop() {
        return gameWorld.getTop();
    }

    @Override
    public void setRight(double d) {
        gameWorld.setRight(d);
    }

    @Override
    public void setLeft(double d) {
        gameWorld.setLeft(d);
    }

    @Override
    public void setTop(double d) {
        gameWorld.setTop(d);
    }

    @Override
    public void setBottom(double d) {
        gameWorld.setBottom(d);
    }

    @Override
    public void addShockWave(Point p) {
        gameWorld.addShockWave(p);
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
