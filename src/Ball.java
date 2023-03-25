public class Ball extends DisplayObject {
    private int speed;
    private int radius;

    public Ball(int color, int x1, int y1, int x2, int y2, int x, int y, int speed, int radius) {
        super(color, x1, y1, x2, y2, x, y);
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
