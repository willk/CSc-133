package a4.game.objects;

import a4.GameWorldProxy;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * Created by William Kinderman on 4/21/15, 7:02 PM.
 */
public class Factory {
    private GameWorldProxy gwp;
    private Stack<Point> ppd, npd;
    private Random r;
    private boolean pylonOne;
    private static int npcCount, pylonNumber;

    public Factory(GameWorldProxy gameWorldProxy, Random random) {
        this.gwp = gameWorldProxy;
        this.r = random;

        pylonNumber = 0;
        npcCount = 0;

        ppd = new Stack<Point>();
        npd = new Stack<Point>();


        ppd.addAll(Arrays.asList(
                        new Point(150, 630),
                        new Point(950, 630),
                        new Point(500, 360),
                        new Point(950, 90),
                        new Point(150, 90)
                )
        );

        npd.addAll(Arrays.asList(
                        new Point(20, 80),
                        new Point(20, 100),
                        new Point(20, 120)
                )
        );

        pylonOne = false;
    }

    public void mkPlayer() {
        if (!pylonOne)
            mkPylon();

        add(new Player(new Point(20, 60), gwp));
    }

    public void mkNPCar() {
        int highestCar = -1;
        NPCar npc;
        Player player = null;

        if (!pylonOne)
            mkPylon();

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
            mkPlayer();

        add(npc);
    }

    public void mkPylon() {
        pylonOne = true;
        Pylon p;

        if (!ppd.isEmpty()) p = new Pylon(ppd.pop(), pylonNumber++, gwp);
        else p = new Pylon(pylonNumber++, gwp);

        add(p);
    }

    public void mkPylon(Point p, int i) {
        add(new Pylon(p, i, gwp));
    }

    public void mkFuelCan() {
        add(new FuelCan(gwp));
    }

    public void mkFuelCan(Point p, int capacity) {
        FuelCan f = new FuelCan(gwp);
        f.setLocation(p);
        f.setCapacity(capacity);
        add(f);
    }

    public void mkBird() {
        add(new Bird());
    }

    public void mkOilSlick() {
        add(new OilSlick());
    }

    public void mkOilSlick(Point p) {add(new OilSlick(p));}

    private Point rPoint() {
        return new Point(r.nextInt(100), r.nextInt(100));
    }

    private void add(GameObject o) {
        gwp.addGameObject(o);
    }
}
