import javax.swing.*;
import java.awt.*;

public class GameField extends JFrame {
    private int width;
    private int height;
    private Color backgroundColor;
    private DisplayCollection displayCollection;
    private MessageBox endMessage;
    private StatusBar statusBar;
    private GameStatistic gameStatistic;

    public GameField(int width, int height) throws InterruptedException {
        this.width = width;
        this.height = height;
        setTitle("Arkanoid");
        displayCollection = new DisplayCollection(width, height);
        getContentPane().add(displayCollection);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        this.backgroundColor = new Color(171, 149, 39);
        getContentPane().setBackground(this.backgroundColor);
        setVisible(true);
    }

    public DisplayCollection getDisplayCollection() {
        return displayCollection;
    }

    public void setDisplayCollection(DisplayCollection displayCollection) {
        this.displayCollection = displayCollection;
    }

    void checkForEndOfGame() {

    }
}
