public abstract class Shape {
    private String color;
    private float speed;
    private float coordinateX;
    private float coordinateY;

    public Shape(String color, float speed, float coordinateX, float coordinateY) {
        this.color = color;
        this.speed = speed;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public String getColor() {
        return color;
    }

    public float getSpeed() {
        return speed;
    }

    public float getCoordinateX() {
        return coordinateX;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    abstract void move();
}
