package a4;

import a4.game.objects.GameCollection;
import a4.game.objects.GameObject;

import java.awt.*;

/**
 * Created by William Kinderman on 3/20/15, 6:07 PM.
 */
public interface IGameWorld {
    GameCollection getGameCollection();

    void addToGraveyard(GameObject o);

    boolean getSound();

    boolean removeGameObject(GameObject o);

    int getHighestPylon();

    double getTime();

    int getLives();

    String getVersion();

    void addGameObject(GameObject o);

    void addFuelCan();

    void addFuelCan(Point p, int capacity);

    void addOilSlick(Point p);

    void addPylon(Point point, int pylon);

    boolean paused();

    void setTemp(Object o);

    Object getTemp();

    void slurp();

    void crash();

    void death();

    double getLeft();

    double getRight();

    double getBottom();

    double getTop();

    void setRight(double d);

    void setLeft(double d);

    void setTop(double d);

    void setBottom(double d);

    void addShockWave(Point p);
}
