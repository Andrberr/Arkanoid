public class Ball extends DisplayObject {
    private int speed;
    private int radius;

    public Ball(int color, int coordinateX, int coordinateY, int coordinateX1, int coordinateY1, int bindX, int bindY, int speed, int radius) {
        super(color, coordinateX, coordinateY, coordinateX1, coordinateY1, bindX, bindY);
        this.speed = speed;
        this.radius = radius;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    void checkCollision(DisplayObject object) {

    }

    @Override
    void draw() {

    }

    @Override
    void move() {

    }
}
