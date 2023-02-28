public class GameField {
    private float width;
    private float height;
    private DisplayObject displayObject;

    public GameField(float width, float height, DisplayObject displayObject) {
        this.width = width;
        this.height = height;
        this.displayObject = displayObject;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public DisplayObject getDisplayObject() {
        return displayObject;
    }

    public void setDisplayObject(DisplayObject displayObject) {
        this.displayObject = displayObject;
    }

    void checkForEndOfGame(){

    }
}
