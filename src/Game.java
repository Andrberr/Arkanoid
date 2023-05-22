import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Game {
    static GameField gameField;
    Players players;


    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    Settings settings;
    static Timer timer;
    static long currTime = 0L;

    StatusBar statusBar;

    static StatusBar bar;
    static Settings sett;

    public void setStatusBar(StatusBar statusBar) {
        this.statusBar = statusBar;
    }

    public Game() throws InterruptedException {
        statusBar = new StatusBar();
        settings = new Settings();
        gameField = new GameField(this, 1100, 685);
        gameField.setStatusBarFields("Andrey", "Beryozkin", "0", "0", "00:00");
        gameField.updateStatusBar();
    }

    public static void main(String[] args) throws InterruptedException {
        new Game();
    }

    public static void gameLoop() throws InterruptedException {
        int delay = 3;
        timer = new Timer(delay, new ActionListener() {
            long startTime = System.currentTimeMillis();

            @Override
            public void actionPerformed(ActionEvent e) {
                move();
                checkCollision();
                try {
                    draw();
                } catch (InterruptedException ex) {
                    System.out.println(e.toString());
                }

                long elapsedTime = System.currentTimeMillis() - startTime;
                if (elapsedTime >= 1000L) {
                    currTime += 1000L;
                    gameField.formatTime(currTime);
                    startTime = System.currentTimeMillis();
                }
            }
        });
    }

    private static void move() {
        for (GameFigure figure : gameField.getDisplayFigures().getFigures()) {
            if (!figure.isStatic) {
                if (!figure.figureMove()) {
                }
            }
        }
    }

    private static void checkCollision() {
        for (GameFigure figure : gameField.getDisplayFigures().getFigures()) {
            if (!figure.isStatic) {
                for (GameFigure anotherFigure : gameField.getDisplayFigures().getFigures()) {
                    if (!figure.equals(anotherFigure)) {
                        boolean withBlock = figure.isCollides(anotherFigure);
                        if (withBlock) gameField.setProgressValue();
                    }
                }
            }
        }
    }

    private static void draw() throws InterruptedException {
        gameField.getDisplayFigures().repaint();
    }

    public void startGame(int width, int height) throws InterruptedException {
        if (gameField != null) {
            gameField.close();
        }
        gameField = new GameField(this, width, height);
        gameField.setStatusBarFields("Andrey", "Beryozkin", "0", "0", "00:00");
        gameField.updateStatusBar();
    }

    public void startNewGame(int width, int height) throws InterruptedException {
        try {
            currTime = 0L;
            startGame(width, height);
            gameLoop();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void resumeGame() throws InterruptedException {
        if (timer != null) timer.start();
        else {
            gameLoop();
            timer.start();
        }
    }

    public void loadFromFile() throws InterruptedException {
        ProxyClass proxy = new ProxyClass();
        ArrayList<GameFigure> figures = new ArrayList<>();
        proxy.deserializeFromJsonFile("save_game.json", figures);

     //   proxy.deserializeFields("save_game.txt", figures);

        int width = 0;
        int height = 0;
        for (GameFigure figure : figures) {
            if (figure instanceof Ball) {
                width = ((Ball) figure).getWidth();
                height = ((Ball) figure).getHeight();
            }
        }

        gameField.close();
        this.statusBar = bar;
        this.settings = sett;
        gameField = new GameField(this, width, height);
        String[] arr = bar.getTime().split(":");
        long t;
        if (arr[0].equals("0")) t = Long.parseLong(arr[1]);
        else t = Long.parseLong(arr[0].concat(arr[1]));
        currTime = t * 1000L;
        gameField.setStatusBarFields(bar.getName(), bar.getSurname(), bar.getDestroyed(), bar.getProgressBar(), bar.getTime());
        gameField.setDisplayObjects(figures);

        for (GameFigure figure : figures) {
            if (figure instanceof Platform) {
                gameField.displayObjects.currentDesk = (Platform) figure;
            }
        }
        gameField.updateStatusBar();
    }

    public void saveInFile() throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        ProxyClass proxy = new ProxyClass();
//        try (PrintWriter writer = new PrintWriter(new FileWriter("save_game.txt", false))) {
//            writer.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (GameFigure figure : gameField.displayObjects.getFigures()) {
//            proxy.serializeField("save_game.txt", figure);
//        }
//        proxy.serializeField("save_game.txt", settings);
//        proxy.serializeField("save_game.txt", statusBar);
         proxy.serializeToJsonFile("save_game.json", gameField.displayObjects.getFigures(), settings, statusBar);
    }

    public void settingsGame() throws InterruptedException {
        settings.setSettingsFrame(new SettingsFrame(settings, this));
    }
}
