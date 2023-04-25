import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    private static GameField gameField;
    private static Players players;
    private static Settings settings;

   static Timer timer;

    public Game() {
        startNewGame();
    }

    public static void main(String[] args) {
        new Game();
    }

    public static void gameMove() {
        int delay = 3;
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
                checkCollision();
                draw();
            }
        });
        timer.start();
    }

    public static void move() {
        for (DisplayObject object : gameField.getDisplayCollection().getObjects()) {
            if (object.isDynamic()) {
                object.move();
            }
        }
    }

    public static void checkCollision() {
        for (DisplayObject object : gameField.getDisplayCollection().getObjects()) {
            if (object.isDynamic()) {
                for (DisplayObject collObject : gameField.getDisplayCollection().getObjects()) {
                    if (!collObject.equals(object)) {
                        if (!(collObject instanceof Block) || ((Block) collObject).isAlive())
                            object.checkCollision(collObject);
                    }
                }
            }
        }
    }

    public static void draw() {
        gameField.getDisplayCollection().repaint();
    }

    public void startNewGame() {
        gameField = new GameField(1170, 720, this);
        gameMove();
    }

    public void pauseGame(Boolean isPaused) {
        if (!isPaused) timer.stop();
        else timer.start();
    }

    public static void resumeGame() {

    }

    public static void finishGame() {

    }

    public void saveGame() {
        ProxySerializationClass proxy = new ProxySerializationClass();
        proxy.serializeToJsonFile("SaveGame.json", gameField.getDisplayCollection().getObjects(), settings);
        //proxy.serializeToTextFile("SaveGame.txt", gameField.getDisplayCollection().getObjects(), settings);
    }

    public void loadGame()
    {
        ProxySerializationClass proxy = new ProxySerializationClass();
        //gameField.getDisplayCollection().setObjects(proxy.deserializeFromTextFile("SaveGame.txt", settings));
        gameField.getDisplayCollection().setObjects(proxy.deserializeFromJsonFile("SaveGame.json", settings));
    }
}