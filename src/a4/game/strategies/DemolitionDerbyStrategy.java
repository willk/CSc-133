package a4.game.strategies;

import a4.GameWorldProxy;
import a4.game.objects.GameObject;
import a4.game.objects.NPCar;
import a4.game.objects.Player;

/**
 * Created by William Kinderman on 3/17/15, 6:10 PM.
 */
public class DemolitionDerbyStrategy implements IStrategy {
    private NPCar npCar;
    private Player player;

    public DemolitionDerbyStrategy(NPCar npCar, GameWorldProxy gameWorldProxy) {
        this.npCar = npCar;
        for (GameObject o : gameWorldProxy.getGameCollection()) {
            if (o instanceof Player)
                this.player = (Player) o;
        }
    }

    @Override
    public double apply() {
        double dy = npCar.getY() - player.getY();
        double dx = npCar.getX() - player.getX();

        npCar.setHeading(Math.toDegrees(Math.atan2(dy, dx)));
        return player.getSpeed() / 5;
    }
}
