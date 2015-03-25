package a2;

import a2.game.objects.GameCollection;
import a2.game.objects.GameObject;
import a2.game.objects.Player;

/**
 * Created by William Kinderman on 3/20/15, 6:37 PM.
 */
public class GameWorldProxy implements IGameWorld, IObservable {
    GameWorld gameWorld;

    public GameWorldProxy(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
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
    public int getTime() {
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
    public void notifyObservers() {
        gameWorld.notifyObservers();
    }
}
