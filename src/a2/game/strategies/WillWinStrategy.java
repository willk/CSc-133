package a2.game.strategies;

import a2.GameWorldProxy;
import a2.Point;
import a2.game.objects.GameObject;
import a2.game.objects.NPCar;
import a2.game.objects.Player;
import a2.game.objects.Pylon;


/**
 * Created by William Kinderman on 3/17/15, 9:18 PM.
 */
public class WillWinStrategy implements IStrategy {
    private final GameWorldProxy gameWorldProxy;
    private NPCar npCar;
    private Point pPoint;

    public WillWinStrategy(NPCar npCar, GameWorldProxy gameWorldProxy) {
        this.npCar = npCar;
        this.gameWorldProxy = gameWorldProxy;

    }

    private void setpPoint() {

        for (GameObject go : gameWorldProxy.getGameCollection()) {
            if (go instanceof Pylon)
                if (((Pylon) go).getSequenceNumber() == npCar.getPylon())
                    pPoint = go.getLocation();
        }
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
        setpPoint();
        npCar.setHeading(
                Math.toDegrees(
                        Math.atan2(
                                npCar.getLocation().getY() - pPoint.getY(),
                                npCar.getLocation().getX() - pPoint.getX()
                        )
                )
        );
        return getPlayerSpeed() - 5;
    }
}
