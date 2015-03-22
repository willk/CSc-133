package a2;

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

    void accelerate();

    void addGameObject(GameObject o);

    void addOilSlick();

    void brake();

    void changeStrategy();

    void collideBird();

    void collideCar(int nPCar);

    void collidePylon(int pylon);

    void enterSlick();

    void exitSlick();

    void left();

    void newColors();

    void pickUpFuel();

    void quit();

    void right();

    void setSound(boolean sound);

    void tick();
}
