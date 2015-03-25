package a2.game.strategies;

import a2.Point;
import a2.game.objects.NPCar;
import a2.game.objects.Player;

/**
 * Created by William Kinderman on 3/17/15, 6:10 PM.
 */
public class DemolitionDerbyStrategy implements IStrategy {
    private NPCar npCar;
    private Player player;

    public DemolitionDerbyStrategy(NPCar npCar, Player player) {
        this.npCar = npCar;
        this.player = player;
    }

    @Override
    public double apply() {
        Point nPoint = npCar.getLocation();
        Point pPoint = player.getLocation();

        npCar.setHeading(
                Math.toDegrees(
                        Math.atan2(
                                nPoint.getY() - pPoint.getY(),
                                nPoint.getX() - pPoint.getX()
                        )
                )
        );
        return player.getSpeed() - 2.5;
    }
}
