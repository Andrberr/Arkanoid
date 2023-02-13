public class Ball extends Shape {
    private final int radius;

    public Ball(String color, float speed, float coordinateX, float coordinateY, int radius) {
        super(color, speed, coordinateX, coordinateY);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    void move() {

    }
}
