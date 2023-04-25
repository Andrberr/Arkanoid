import java.awt.*;
import java.io.Serializable;

public class StatusBar extends DisplayObject implements Serializable {
    private TextBox[] textBoxes;
    private Button menuButton;
    private Menu menu;
    private GameStatistic gameStatistic;

    public StatusBar(TextBox[] textBoxes, int x1, int y1, int x2, int y2, int x, int y, int color, boolean isDynamic) {
        super(x1, y1, x2, y2, x, y, color, isDynamic);
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
    void draw(Graphics2D g2d) {

    }

    @Override
    void move() {

    }

    @Override
    public void serializeToTextFile(String filename) {

    }

    @Override
    public void deserializeFromTextFile(String filename) {

    }
}
