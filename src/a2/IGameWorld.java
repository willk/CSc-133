package a2;

import a2.game.objects.GameCollection;
import a2.game.objects.GameObject;
import a2.game.objects.Player;

/**
 * Created by William Kinderman on 3/20/15, 6:07 PM.
 */
public interface IGameWorld {
    GameCollection getGameCollection();

    boolean getSound();

    boolean removeGameObject(GameObject o);

    int getHighestPylon();

    int getTime();

    int getLives();

    String getVersion();

    void addGameObject(GameObject o);

    Player getPlayer();
}
