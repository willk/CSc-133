package a1;

/**
 * Created by willk on 2/20/15.
 */
public class OilSlick extends Fixed {
    private double length;
    private double width;

    public OilSlick() {
        this.width = r.nextInt(45) + 10;
        this.length = r.nextInt(45) + 10;
    }

    public double getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "OilSlick: " + super.toString() +
                ", length=" + this.getLength() +
                ", width=" + this.getWidth();
    }
}
