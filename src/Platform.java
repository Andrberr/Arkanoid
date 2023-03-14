public class Platform extends DisplayObject {
    private int speed;

    public Platform(int color, int coordinateX, int coordinateY, int coordinateX1, int coordinateY1, int bindX, int bindY, int speed) {
        super(color, coordinateX, coordinateY, coordinateX1, coordinateY1, bindX, bindY);
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
