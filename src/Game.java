import java.awt.*;

public class Game {
    private static GameField gameField;
    private static Players players;
    private static Settings settings;

    public static void main(String[] args) throws InterruptedException {
        startGame();
    }

    public static void startGame() throws InterruptedException {
        gameField = new GameField(1170, 720);
        gameDvizh();
    }

    public static void gameDvizh() throws InterruptedException {
        while (true) {
            move();
            checkCollision();
            draw();
            Thread.sleep(8);
        }
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

    public static void pauseGame() {

    }

    public static void resumeGame() {

    }

    public static void finishGame() {

    }

    public static void saveGame() {

    }

    public static void loadGame() {

    }
}