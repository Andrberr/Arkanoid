public class StatusBar extends DisplayObject {
    private TextBox[] textBoxes;
    private Button menuButton;
    private Menu menu;
    private GameStatistic gameStatistic;

    public StatusBar(TextBox[] textBoxes, int color, int x1, int y1, int x2, int y2, int x, int y) {
        super(color, x1, y1, x2, y2, x, y);
        this.textBoxes = textBoxes;
    }

    public GameStatistic getGameStatistic() {
        return gameStatistic;
    }

    void drawBar() {

    }

    void showMenu() {

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
