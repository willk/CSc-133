package a3.game.objects;

import a3.GameWorldProxy;
import a3.game.strategies.DemolitionDerbyStrategy;
import a3.game.strategies.WillWinStrategy;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * Created by William Kinderman on 4/21/15, 7:02 PM.
 */
public class Factory {
    private GameWorldProxy gwp;
    private Stack<Point> ppd;
    private Stack<Point> npd;
    private boolean pylonOne;
    private boolean start;
    private Random r;
    private static int npcCount;

    public Factory(GameWorldProxy gameWorldProxy, Random random) {
        this.gwp = gameWorldProxy;
        this.r = random;

        npcCount = 0;

        ppd = new Stack<Point>();
        npd = new Stack<Point>();

        ppd.addAll(Arrays.asList(
                        new Point(500, 500),
                        new Point(125, 875),
                        new Point(875, 875),
                        new Point(875, 125),
                        new Point(125, 125)
                )
        );

        npd.addAll(Arrays.asList(
                        new Point(125, 165),
                        new Point(125, 145),
                        new Point(125, 105)
                )
        );

        pylonOne = false;
        start = true;
    }

    public void start() {
        start = false;
    }

    public Player mkPlayer() {
        if (!pylonOne)
            gwp.addGameObject(mkPylon());

        Point p = rPoint();

        if (start)
            for (GameObject go : gwp.getGameCollection())
                if (go instanceof Pylon)
                    if (((Pylon) go).getSequenceNumber() == 1)
                        p = go.getLocation();

        return new Player(p);
    }

    public NPCar mkNPCar() {
        int highestCar = -1;
        NPCar npc;
        Player player = null;

        if (!pylonOne)
            gwp.addGameObject(mkPylon());

        if (!npd.isEmpty()) {
            npc = new NPCar(npd.pop(), npcCount++, gwp);

        } else {
            for (GameObject o : gwp.getGameCollection())
                if (o instanceof NPCar)
                    if (((NPCar) o).getUID() > highestCar)
                        highestCar = ((NPCar) o).getUID();

            npc = new NPCar(rPoint(), highestCar, gwp);
        }

        for (GameObject o : gwp.getGameCollection())
            if (o instanceof Player)
                player = (Player) o;

        if (player == null)
            gwp.addGameObject(player = mkPlayer());

        if (r.nextInt(2) == 0)
            npc.setStrategy(new DemolitionDerbyStrategy(npc, gwp));
        else
            npc.setStrategy(new WillWinStrategy(npc, gwp));

        return npc;
    }

    public Pylon mkPylon() {
        pylonOne = true;

        if (!ppd.isEmpty()) {
            return new Pylon(ppd.pop(), gwp.getHighestPylon() + 1);
        }

        return new Pylon(gwp.getHighestPylon() + 1);
    }

    public FuelCan mkFuelCan() {
        return new FuelCan();
    }

    public Bird mkBird() {
        return new Bird();
    }

    public OilSlick mkOilSlick() {
        return new OilSlick();
    }

    private Point rPoint() {
        return new Point(r.nextInt(100), r.nextInt(100));
    }
}
