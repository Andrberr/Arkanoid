import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class Game {
    static GameField gameField;
    Players players;
    Settings settings;
    static Timer timer;
    static long currTime = 0L;
    String name = "";
    String surname = "";


    public Game() {
        PlayerForm form = new PlayerForm();
        form.submitButton.addActionListener(e -> {
            if (!form.nameField.getText().equals("") && !form.surnameField.getText().equals("")) {
                settings = new Settings();
                settings.screen = 0;
                name = form.nameField.getText();
                surname = form.surnameField.getText();
                form.dispose();
                try {
                    gameField = new GameField(this, 1100, 685, name, surname);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) {
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
        gameField = new GameField(this, width, height, name, surname);
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
        if (gameField == null)
            gameField = new GameField(this, 1100, 685, name, surname);
        ProxyClass proxy = new ProxyClass();
        proxy.deserializeFromJsonFile("save_game.json", gameField.displayObjects.getFigures(), settings);
        //proxy.deserializeFields("save_game.txt", gameField.displayObjects.getFigures());
        for (GameFigure figure : gameField.displayObjects.getFigures()) {
            if (figure instanceof Platform) {
                gameField.displayObjects.currentDesk = (Platform) figure;
                break;
            }
        }
    }

    public void saveInFile() throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        ProxyClass proxy = new ProxyClass();
        try (PrintWriter writer = new PrintWriter(new FileWriter("save_game.txt", false))) {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        for (GameFigure figure : gameField.displayObjects.getFigures()) {
//            proxy.serializeField("save_game.txt", figure);
//        }
//        proxy.serializeField("save_game.txt", settings);
         proxy.serializeToJsonFile("save_game.json", gameField.displayObjects.getFigures(), settings);
    }

    public void settingsGame() throws InterruptedException {
        settings.setSettingsFrame(new SettingsFrame(settings, this));
    }
}
