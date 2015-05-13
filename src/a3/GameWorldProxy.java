package a3;

import a3.game.objects.GameCollection;
import a3.game.objects.GameObject;
import a3.game.objects.Player;

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
    public Player getPlayer() {
        return gameWorld.getPlayer();
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
    public void addFuelCan(Point p) {
        gameWorld.addFuelCan(p);
    }

    @Override
    public void addOilSlick(Point p) {
        gameWorld.addFuelCan(p);
    }

    @Override
    public void notifyObservers() {
        gameWorld.notifyObservers();
    }
}
