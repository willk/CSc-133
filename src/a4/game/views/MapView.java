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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

/**
 * Created by William Kinderman on 3/14/15, 6:45 PM, 6:45 PM.
 */
public class MapView extends JPanel implements IObserver, MouseListener {
    GameWorldProxy gwp;
    Boolean fuel, pylon;

    int left, right, top, bottom;
    AffineTransform wToND, ndToScreen, vtm;

    public MapView(GameWorldProxy gwp) {
        this.gwp = gwp;

        this.fuel = false;
        this.pylon = false;

        left = gwp.getXMin();
        right = gwp.getXMax();
        top = gwp.getYMax();
        bottom = gwp.getYMin();

        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(66, 11, 127)));
        this.addMouseListener(this);

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

        repaint();
    }

    private int getCapacity() {
        String s = "";
        try {
            s = JOptionPane.showInputDialog(
                    null,
                    "Enter the capacity of the fuel can."
            );
        } catch (NumberFormatException error) {
            System.out.println(error);
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
            System.out.println(error);
        }

        return Integer.parseInt(newPylon);
    }

    private void select(Point p, boolean controlDown) {

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

    private AffineTransform worldToNDX(double width, double height, double left, double bottom) {
        AffineTransform at = new AffineTransform();
        at.setToIdentity();
        at.scale(1 / width, 1 / height);
        at.translate(-left, -bottom);
        return at;
    }

    private AffineTransform ndToScreen(double panelWidth, double panelHeight) {
        AffineTransform at = new AffineTransform();
        at.setToIdentity();
        at.translate(0, panelHeight);
        at.scale(panelWidth, -panelHeight);
        return at;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        AffineTransform at = g2d.getTransform();
        wToND = worldToNDX(right, top, left, bottom);
        ndToScreen = ndToScreen(this.getWidth(), this.getHeight());
        vtm = (AffineTransform) ndToScreen.clone();
        vtm.concatenate(wToND);
        g2d.transform(vtm);

        for (Object o : gwp.getGameCollection())
            if (o instanceof IDrawable)
                ((IDrawable) o).draw(g);

        g2d.setTransform(at);
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
                gwp.addFuelCan(e.getPoint(), this.getCapacity());
            } else if (pylon) {
                togglePylon();
                fuel = false;
                gwp.addPylon(e.getPoint(), this.getPylon());
            } else {
                select(e.getPoint(), e.isControlDown());
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
