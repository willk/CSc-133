package a2;

public class FuelCan extends Fixed {
    private int size;

    public FuelCan() {
        // When instantiating a fuel can make sure th value is between %5 and %30
        this.setColor();
        this.setLocation();
        this.size = r.nextInt(25) + 5;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "FuelCan: " + super.toString() +
                ", size=" + this.getSize();
    }
}
