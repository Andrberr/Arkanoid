import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameField extends JFrame {
    private int width;
    private int height;
    private Color backgroundColor;
    private DisplayCollection displayCollection;
    private MessageBox endMessage;
    private StatusBar statusBar;
    private GameStatistic gameStatistic;

    Menu menu;



    public GameField(int width, int height, Game game) {
        this.width = width;
        this.height = height;
        setTitle("Arkanoid");
        displayCollection = new DisplayCollection(width, height);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(displayCollection, BorderLayout.CENTER);
        JButton menuButton = new JButton("Menu");
        getContentPane().add(menuButton, BorderLayout.EAST);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuButton.setVisible(false);
                showMenu();
            }
        });
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        this.backgroundColor = new Color(171, 149, 39);

        setVisible(true);

        menu = new Menu(game, getContentPane());
    }

    public void stopGame() {
        this.dispose();
    }

    public void showMenu() {
        menu.drawMenu();
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
