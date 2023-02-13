public class DestroyedBlock {
    private String color;
    private float coordinateX;
    private float coordinateY;
    private float width;
    private float height;

    public DestroyedBlock(String color, float coordinateX, float coordinateY, float width, float height) {
        this.color = color;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.width = width;
        this.height = height;
    }

    public String getColor() {
        return color;
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
}
