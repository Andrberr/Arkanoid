public abstract class DisplayObject {
    private int coordinateX;
    private int coordinateY;
    private int coordinateX1;
    private int coordinateY1;
    private int bindX;
    private int bindY;
    private int color;


    public DisplayObject(int color, int coordinateX, int coordinateY, int coordinateX1, int coordinateY1, int bindX, int bindY) {
        this.color = color;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.coordinateX1 = coordinateX1;
        this.coordinateY1 = coordinateY1;
        this.bindX = bindX;
        this.bindY = bindY;
    }

    public float getCoordinateX() {
        return coordinateX;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public int getColor() {
        return color;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public float getCoordinateX1() {
        return coordinateX1;
    }

    public void setCoordinateX1(int coordinateX1) {
        this.coordinateX1 = coordinateX1;
    }

    public float getCoordinateY1() {
        return coordinateY1;
    }

    public void setCoordinateY1(int coordinateY1) {
        this.coordinateY1 = coordinateY1;
    }

    public float getBindX() {
        return bindX;
    }

    public void setBindX(int bindX) {
        this.bindX = bindX;
    }

    public float getBindY() {
        return bindY;
    }

    public void setBindY(int bindY) {
        this.bindY = bindY;
    }

    public void setColor(int color) {
        this.color = color;
    }

    abstract void checkCollision(DisplayObject object);
    abstract void draw();
    abstract void move();

}
