package a2;

import java.awt.*;

public class Pylon extends Fixed {
    private double radius;
    private int sequenceNumber;

    public Pylon(Point location, int number) {
        this.setRadius(20);
        this.setColor(Color.GRAY);
        this.setLocation(location);
        this.setSequenceNumber(number);
    }

    public void setColor() {
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public String toString() {
        return "Pylon: " + super.toString() +
                ", radius=" + this.getRadius() +
                ", sequenceNumber=" + this.getSequenceNumber();
    }
}
