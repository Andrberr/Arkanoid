public class Platform extends GameObject {
    private float speed;

    public Platform(String color, float coordinateX, float coordinateY, int width, int height, float speed) {
        super(color, coordinateX, coordinateY, width, height);
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    void checkCollision(GameObject object) {

    }

    @Override
    void draw() {

    }
}
