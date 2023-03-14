public class GameField {
    private int width;
    private int height;
    private DisplayObject[] objects;
    private int color;
    private int backgroundColor;
    private MessageBox endMessage;
    private StatusBar statusBar;
    private GameStatistic gameStatistic;

    public GameField(int width, int height, DisplayObject[] objects, int color, int backgroundColor) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.objects = objects;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public DisplayObject[] getObjects() {
        return objects;
    }

    public void setObjects(DisplayObject[] objects) {
        this.objects = objects;
    }

    public MessageBox getEndMessage() {
        return endMessage;
    }

    void addObject(DisplayObject object){

    }

    void deleteObject(DisplayObject object){

    }

    void checkForEndOfGame() {

    }
}
