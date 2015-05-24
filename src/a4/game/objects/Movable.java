package a4.game.objects;

public abstract class Movable extends GameObject {

    private double speed;
    private double heading;

    public void move(double time) {
        double theta;

        theta = Math.toRadians(getHeading() + 180);

        double dx = Math.cos(theta) * (this.getSpeed() / 10);
        double dy = Math.sin(theta) * (this.getSpeed() / 10);

        getTranslate().translate(dx, dy);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = (heading % 360 + 360) % 360;
        this.setRotate(this.heading);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", speed=" + this.getSpeed() +
                ", heading=" + this.getHeading();
    }
}
