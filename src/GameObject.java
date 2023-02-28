public abstract class GameObject{
    private float coordinateX;
    private float coordinateY;
    private float width;
    private float height;
    private String color;

    public GameObject(String color, float coordinateX, float coordinateY, float width, float height) {
        this.color = color;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.width = width;
        this.height = height;
    }

    public float getCoordinateX() {
        return coordinateX;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String getColor() {
        return color;
    }

    public void setCoordinateX(float coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(float coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setColor(String color) {
        this.color = color;
    }

    abstract void checkCollision(GameObject object);
    abstract void draw();
}
