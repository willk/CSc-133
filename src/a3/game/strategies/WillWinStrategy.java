package a3.game.strategies;

import a3.GameWorldProxy;
import a3.Point;
import a3.game.objects.GameObject;
import a3.game.objects.NPCar;
import a3.game.objects.Player;
import a3.game.objects.Pylon;


/**
 * Created by William Kinderman on 3/17/15, 9:18 PM.
 */
public class WillWinStrategy implements IStrategy {
    private final GameWorldProxy gameWorldProxy;
    private NPCar npCar;
    private Point pylon;

    public WillWinStrategy(NPCar npCar, GameWorldProxy gameWorldProxy) {
        this.npCar = npCar;
        this.gameWorldProxy = gameWorldProxy;
        this.getPylonLocation();
    }

    private void getPylonLocation() {
        for (GameObject go : gameWorldProxy.getGameCollection())
            if (go instanceof Pylon)
                if (((Pylon) go).getSequenceNumber() == npCar.getPylon())
                    pylon = go.getLocation();
    }

    private double getPlayerSpeed() {
        double speed = 0;
        for (GameObject go : gameWorldProxy.getGameCollection())
            if (go instanceof Player)
                speed = ((Player) go).getSpeed();
        return speed;
    }

    @Override
    public double apply() {
        getPylonLocation();

        double dy = npCar.getY() - pylon.getY();
        double dx = npCar.getX() - pylon.getX();


        npCar.setHeading(Math.toDegrees(Math.atan2(dy, dx)));
        return getPlayerSpeed() / 5;
    }
}
