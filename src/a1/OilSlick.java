package a1;

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

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "OilSlick: " + super.toString() +
                ", length=" + this.getLength() +
                ", width=" + this.getWidth();
    }
}
