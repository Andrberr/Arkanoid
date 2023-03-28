import java.awt.*;

public class Game {
    private static GameField gameField;
    private static Players players;

    public static void main(String[] args) throws InterruptedException {
        startGame();
    }

    public static void startGame() throws InterruptedException {
        gameField = new GameField(1170, 720);
        DisplayObject[] objects = gameField.getDisplayCollection().getObjects();

        boolean isFinish = false;
        while (!isFinish) {
            for (DisplayObject object : objects) {
                if (object.isDynamic()) {
                    for (DisplayObject collObject : objects) {
                        if (!collObject.equals(object)) object.checkCollision(collObject);
                    }
                    object.move();
                    if (object instanceof Ball && ((Ball) object).isFinish()) {
                        isFinish = true;
                        break;
                    }
                }
            }
            gameField.getDisplayCollection().repaint();
            Thread.sleep(8);
        }
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