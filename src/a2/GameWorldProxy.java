package a2;

/**
 * Created by William Kinderman on 3/20/15, 6:37 PM.
 */
public class GameWorldProxy implements IGameWorld, IObservable {
    GameWorld gw;

    public GameWorldProxy(GameWorld gameWorld) {
        gw = gameWorld;
    }

    @Override
    public GameCollection getGameCollection() {
        return gw.getGameCollection();
    }

    @Override
    public void addGameObject(GameObject o) {
        gw.addGameObject(o);
    }

    @Override
    public boolean removeGameObject(GameObject o) {
        return gw.removeGameObject(o);
    }

    @Override
    public void accelerate() {
        gw.accelerate();
    }

    @Override
    public void addOilSlick() {
        gw.addOilSlick();
    }

    @Override
    public void brake() {
        gw.brake();
    }

    @Override
    public void changeStrategy() {
        gw.changeStrategy();
    }

    @Override
    public void left() {
        gw.left();
    }

    @Override
    public void quit() {
        gw.quit();
    }

    @Override
    public void right() {
        gw.right();
    }

    @Override
    public void tick() {
        gw.tick();
    }

    @Override
    public void pickUpFuel() {
        gw.pickUpFuel();
    }

    @Override
    public void newColors() {
        gw.newColors();
    }

    @Override
    public String getVersion() {
        return gw.getVersion();
    }

    @Override
    public boolean getSound() {
        return gw.getSound();
    }

    @Override
    public void setSound(boolean sound) {
        gw.setSound(sound);
    }

    @Override
    public void enterSlick() {
        gw.enterSlick();
    }

    @Override
    public void exitSlick() {
        gw.exitSlick();
    }

    @Override
    public void collideBird() {
        gw.collideBird();
    }

    @Override
    public void collidePylon(int pylon) {
        gw.collidePylon(pylon);
    }

    @Override
    public void collideCar(int nPCar) {
        gw.collideCar(nPCar);
    }

    @Override
    public int getHighestPylon() {
        return gw.getHighestPylon();
    }

    @Override
    public int getTime() {
        return gw.getTime();
    }

    @Override
    public int getLives() {
        return gw.getLives();
    }

    @Override
    public void addObserver(IObserver o) {
        gw.addObserver(o);
    }

    @Override
    public void notifyObservers() {
        gw.notifyObservers();
    }
}
