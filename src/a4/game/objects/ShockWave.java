package a4.game.objects;

import a4.GameWorldProxy;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by William Kinderman on 5/17/15, 8:07 PM.
 */
public class ShockWave extends Movable implements IDrawable {
    private static final int MAXLIFE = 70;
    private Point[] cps;
    private int life;
    private double sx, bx, sy, by;

    public ShockWave(Point p, GameWorldProxy gwp) {

        cps = new Point[] {
                new Point(0, 0),
                new Point(r.nextInt(10) + 25, r.nextInt(20) + 55),
                new Point(r.nextInt(10) + 25, r.nextInt(20) + 55),
                new Point(0, 100)
        };

        sx = Double.MAX_VALUE;
        sy = Double.MAX_VALUE;
        bx = Double.MIN_VALUE;
        by = Double.MIN_VALUE;

        this.setHeading(r.nextInt() % 360);
        this.setRotate(-this.getHeading());
        this.setTranslate(p.getX(), p.getY());
        this.setGWP(gwp);

        for (Point cp : cps) {
            if (sx <= cp.getX()) sx = cp.getX();
            if (sy <= cp.getY()) sy = cp.getY();
            if (bx > cp.getX()) bx = cp.getX();
            if (by > cp.getY()) by = cp.getY();
        }
    }


    @Override
    public void move(double time) {
        if (++life > this.MAXLIFE) getGWP().addToGraveyard(this);
        super.move(time);
    }

    private void drawCurve(Point[] cpv, int level, Graphics2D g2d) {
        Point[] lsv = new Point[4];
        Point[] rsv = new Point[4];
        if (straightEnough (cpv) || (level > 15) )
            g2d.drawLine((int)cpv[0].getX(),(int) cpv[0].getY(),(int) cpv[3].getX(), (int)cpv[3].getY());
        else {
            divideBCurve(cpv, lsv, rsv) ;
            drawCurve(lsv, level + 1, g2d) ;
            drawCurve(rsv, level + 1, g2d) ;
        }
    }

    private boolean straightEnough (Point[] cpv) {
        Double d1 = lengthOf(cpv[0],cpv[1]) + lengthOf(cpv[1], cpv[2]) + lengthOf(cpv[2], cpv[3]);
        Double d2 = lengthOf(cpv[0],cpv[3]) ;

        if ( Math.abs(d1 - d2) < 0.0001f )
            return true ;
        else
            return false ;
    }

    private Double lengthOf(Point d1, Point d2) {
        Double answer = null;
        Double X = Math.pow((d1.getX() - d2.getX()), 2);
        Double Y = Math.pow((d1.getY() - d2.getY()), 2);
        answer = Math.sqrt(X+Y);
        return answer;
    }

    private void divideBCurve(Point[] Q, Point[] R, Point[] S) {
        Point T = bMath(Q[1], Q[2]);
        R[0]=Q[0];
        R[1]= bMath(Q[0], Q[1]);
        R[2]= bMath(R[1], T);
        S[3]=Q[3];
        S[2]= bMath(Q[3], Q[2]);
        S[1]= bMath(S[2], T);
        R[3]= bMath(R[2], S[1]);
        S[0]=R[3];
    }

    private Point bMath(Point p1, Point p2){
        return new Point(round(p1.getX() / 2.0d + p2.getX() / 2.0d), round(p1.getY() / 2.0d + p2.getY() / 2.0d));
    }

    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform at = g2d.getTransform();
        g2d.transform(getTranslate());
        g2d.transform(getScale());
        g2d.transform(getRotate());

        g2d.setColor(new Color(66, 11, 127));
        drawCurve(cps, 0, g2d);
        g2d.setColor(new Color(249, 244, 128));

        g2d.drawLine((int) cps[0].getX(), (int) cps[0].getY(), (int) cps[1].getX(), (int) cps[1].getY());
        g2d.drawLine((int) cps[1].getX(), (int) cps[1].getY(), (int) cps[2].getX(), (int) cps[2].getY());
        g2d.drawLine((int)cps[2].getX(),(int) cps[2].getY(),(int) cps[3].getX(),(int) cps[3].getY());

        g2d.setTransform(at);
    }
}
