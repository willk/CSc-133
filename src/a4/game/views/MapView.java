package a4.game.views;

import a4.GameWorldProxy;
import a4.IObservable;
import a4.IObserver;
import a4.game.commands.*;
import a4.game.objects.GameObject;
import a4.game.objects.IDrawable;
import a4.game.objects.ISelectable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

/**
 * Created by William Kinderman on 3/14/15, 6:45 PM, 6:45 PM.
 */
public class MapView extends JPanel implements IObserver, MouseListener, MouseMotionListener, MouseWheelListener {
    private double worldLeft, worldRight, worldTop, worldBottom;
    AffineTransform inverse;
    GameWorldProxy gwp;
    Boolean fuel, pylon;

    public MapView(GameWorldProxy gwp) {

        this.fuel = false;
        this.pylon = false;
        this.gwp = gwp;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        this.addMouseListener(this);
        this.addMouseWheelListener(this);
        this.addMouseMotionListener(this);

        worldLeft = gwp.getLeft();
        worldRight = gwp.getRight();
        worldTop = gwp.getTop();
        worldBottom = gwp.getBottom();

        inverse = new AffineTransform();

        // AT stuffs:

        // Create key maps.
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), KeyEvent.VK_UP);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, 0), KeyEvent.VK_O);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), KeyEvent.VK_DOWN);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), KeyEvent.VK_SPACE);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), KeyEvent.VK_LEFT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), KeyEvent.VK_Q);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), KeyEvent.VK_RIGHT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), KeyEvent.VK_T);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), KeyEvent.VK_DELETE);

        // Populate the action map.
        ActionMap actionMap = this.getActionMap();
        actionMap.put(KeyEvent.VK_UP, Accelerate.getInstance());
        actionMap.put(KeyEvent.VK_O, AddOilSlick.getInstance());
        actionMap.put(KeyEvent.VK_DOWN, Brake.getInstance());
        actionMap.put(KeyEvent.VK_SPACE, ChangeStrategy.getInstance());
        actionMap.put(KeyEvent.VK_LEFT, TurnLeft.getInstance());
        actionMap.put(KeyEvent.VK_Q, Quit.getInstance());
        actionMap.put(KeyEvent.VK_RIGHT, TurnRight.getInstance());
        actionMap.put(KeyEvent.VK_T, Tick.getInstance());
        actionMap.put(KeyEvent.VK_DELETE, Delete.getInstance());
    }

    private int getCapacity() {
        String s = "";
        try {
            s = JOptionPane.showInputDialog(
                    null,
                    "Enter the capacity of the fuel can."
            );
        } catch (NumberFormatException error) {
            error.printStackTrace();
        }

        return Integer.parseInt(s);
    }

    private int getPylon() {
        int pylon = gwp.getHighestPylon();

        String newPylon = Integer.toString(pylon);
        try {
            newPylon = JOptionPane.showInputDialog(
                    null,
                    "Enter a collidePylon number.",
                    pylon + 1
            );
        } catch (NumberFormatException error) {
            error.printStackTrace();
        }

        return Integer.parseInt(newPylon);
    }

    private Point p2p(Point2D p) {
        return new Point((int) Math.round(p.getX()), (int) Math.round(p.getY()));
    }

    private void select(Point p, boolean controlDown) {

        p = p2p(inverse.transform(p, null));

        if (controlDown) {
            for (GameObject o : gwp.getGameCollection()) {
                if (o instanceof ISelectable) {
                    if (((ISelectable) o).contains(p)) {
                        ((ISelectable) o).select(!((ISelectable) o).selected());
                    }
                }
            }
        } else {
            for (GameObject o : gwp.getGameCollection()) {
                if (o instanceof ISelectable) {
                    if (((ISelectable) o).contains(p)) {
                        ((ISelectable) o).select(!((ISelectable) o).selected());
                    } else {
                        ((ISelectable) o).select(false);
                    }
                }
            }
        }

        this.repaint();
    }

    public void toggleFuel() {
        fuel = !fuel;
    }

    public void togglePylon() {
        pylon = !pylon;
    }

    public AffineTransform WorldToND() {
        AffineTransform at = new AffineTransform();
        at.setToIdentity();
        at.scale(1 / (worldRight - worldLeft), 1 / (worldTop - worldBottom));
        at.translate(-worldLeft, -worldBottom);
        return at;
    }

    public AffineTransform NDToScreen() {
        AffineTransform at = new AffineTransform();
        at.setToIdentity();
        at.translate(0, this.getHeight());
        at.scale(this.getWidth(), -this.getHeight());
        return at;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform at = g2d.getTransform();

        AffineTransform wToND = WorldToND();
        AffineTransform nDToS = NDToScreen();
        AffineTransform vtm = (AffineTransform) nDToS.clone();
        vtm.concatenate(wToND);
        g2d.transform(vtm);

        try {
            inverse = vtm.createInverse();
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }

        for (Object o : gwp.getGameCollection())
            if (o instanceof IDrawable)
                ((IDrawable) o).draw(g2d);

        g2d.transform(at);
    }

    @Override
    public void update(IObservable gwp) {
        this.gwp = (GameWorldProxy) gwp;
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gwp.paused()) {
            if (fuel) {
                toggleFuel();
                pylon = false;
                gwp.addFuelCan(p2p(inverse.transform(e.getPoint(), null)), this.getCapacity());
            } else if (pylon) {
                togglePylon();
                fuel = false;
                gwp.addPylon(p2p(inverse.transform(e.getPoint(), null)), this.getPylon());
            } else {
                select(e.getPoint(), e.isControlDown());
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        // pan
        Point oldP = this.getMousePosition();
        Point newP = p2p(inverse.transform(e.getPoint(), null));

        if (!gwp.paused()) {
            if (newP != null && oldP != null) {
                double x = oldP.getX() - newP.getX();
                double y = oldP.getY() - newP.getY();
                worldLeft += x * 0.25;
                worldRight += x * 0.25;
                worldTop += y * 0.25;
                worldBottom += y * 0.25;

                gwp.setBottom(worldBottom);
                gwp.setTop(worldTop);
                gwp.setLeft(worldLeft);
                gwp.setRight(worldRight);

                this.repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double height = worldTop - worldBottom;
        double width = worldRight - worldLeft;

        if (!gwp.paused()) {
            if (e.getWheelRotation() < 0) {
                worldLeft += width * 0.05;
                worldRight -= width * 0.05;
                worldTop -= height * 0.05;
                worldBottom += height * 0.05;
            } else {
                worldLeft -= width * 0.05;
                worldRight += width * 0.05;
                worldTop += height * 0.05;
                worldBottom -= height * 0.05;
            }

            gwp.setBottom(worldBottom);
            gwp.setTop(worldTop);
            gwp.setLeft(worldLeft);
            gwp.setRight(worldRight);

            repaint();
        }
    }
}
