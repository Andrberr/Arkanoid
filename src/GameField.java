import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class GameField extends JFrame implements Observer {
    DisplayObjects displayObjects;

    Game game;

    int width;
    int height;

    double koef = 1;
    boolean flag = true;

    int progressValue = 0;

    int score = 0;

    int textSize = 0;
    int spacing = 0;

    JProgressBar progressBar;
    JButton button;
    JTextField textField1;
    JTextField textField2;
    JTextField textField3;
    JTextField textField4;


    public GameField(Game game, int width, int height) throws InterruptedException {
        this.game = game;
        this.width = width;
        this.height = height;

        int dx = 0;
        int dy = 0;

        switch (width) {
            case 1100 -> {
                koef = 1;
                textSize = 14;
                spacing = 30;
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
            case 950 -> {
                koef = 0.8;
                textSize = 12;
                spacing = 22;
                dx = 200;
                dy = 100;
            }
            case 850 -> {
                koef = 0.7;
                textSize = 10;
                spacing = 20;
                dx = 180;
                dy = 100;
            }
            case 800 -> {
                koef = 0.64;
                textSize = 10;
                spacing = 12;
                dx = 180;
                dy = 100;
            }
            case 700 -> {
                koef = 0.55;
                textSize = 10;
                spacing = 8;
                dx = 180;
                dy = 100;
            }
        }

        setTitle("Arkanoid");

        //Menu
        JPanel menuPanel = new JPanel(new GridLayout(8, 1, 0, 5));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // добавляем отступы в 10 пикселей сверху, снизу, слева и справа
        JButton resumeGameButton = new JButton("Resume game");
        resumeGameButton.setVisible(false);
        JButton settingsGameButton = new JButton("Settings");
        settingsGameButton.setVisible(false);
        JButton loadGameButton = new JButton("Load game");
        loadGameButton.setVisible(false);
        JButton saveGameButton = new JButton("Save game");
        saveGameButton.setVisible(false);
        JButton exitButton = new JButton("Exit");
        exitButton.setVisible(false);

        menuPanel.add(resumeGameButton);
        menuPanel.add(settingsGameButton);
        menuPanel.add(loadGameButton);
        menuPanel.add(saveGameButton);
        menuPanel.add(exitButton);
        resumeGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    game.resumeGame();
                    resumeGameButton.setVisible(!resumeGameButton.isVisible());
                    settingsGameButton.setVisible(!settingsGameButton.isVisible());
                    loadGameButton.setVisible(!loadGameButton.isVisible());
                    saveGameButton.setVisible(!saveGameButton.isVisible());
                    exitButton.setVisible(!exitButton.isVisible());
                    getContentPane().getComponent(1).requestFocus();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        settingsGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    game.settingsGame();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    game.loadFromFile();
                    game.resumeGame();
                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
                            long time = System.currentTimeMillis();
                            while (System.currentTimeMillis() < time + 20L) {
                            }
                            Game.timer.stop();
                        }
                    };

                    Thread thread = new Thread(myRunnable);
                    thread.start();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Game.timer.stop();
                    game.saveInFile();
                } catch (InterruptedException | NoSuchFieldException | IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.timer != null) Game.timer.stop();
                System.exit(0);
            }
        });


        //Objects
        displayObjects = new DisplayObjects(width, height, koef);

        //ProgressBar
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setMaximum(displayObjects.getBrokeKol());
        progressBar.setValue(0);

        button = new JButton("Menu");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resumeGameButton.setVisible(!resumeGameButton.isVisible());
                settingsGameButton.setVisible(!settingsGameButton.isVisible());
                loadGameButton.setVisible(!loadGameButton.isVisible());
                saveGameButton.setVisible(!saveGameButton.isVisible());
                exitButton.setVisible(!exitButton.isVisible());
                if (resumeGameButton.isVisible()) {
                    if (Game.timer != null) Game.timer.stop();
                }
            }
        });

        textField1 = new JTextField();
        textField1.setEditable(false);
        textField2 = new JTextField();
        textField2.setEditable(false);
        textField3 = new JTextField();
        textField3.setEditable(false);
        textField4 = new JTextField();
        textField4.setEditable(false);
        //textField4.setPreferredSize(new Dimension(50, 20));

        Font font = new Font(textField1.getFont().getName(), Font.PLAIN, textSize);
        textField1.setFont(font);
        font = new Font(textField2.getFont().getName(), Font.PLAIN, textSize);
        textField2.setFont(font);
        font = new Font(textField3.getFont().getName(), Font.PLAIN, textSize);
        textField3.setFont(font);
        font = new Font(textField4.getFont().getName(), Font.PLAIN, textSize);
        textField4.setFont(font);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(progressBar);
        panel.add(Box.createHorizontalStrut(spacing)); // Add horizontal spacing
        panel.add(button);
        panel.add(Box.createHorizontalStrut(spacing)); // Add horizontal spacing
        panel.add(textField1);
        panel.add(Box.createHorizontalStrut(spacing)); // Add horizontal spacing
        panel.add(textField2);
        panel.add(Box.createHorizontalStrut(spacing)); // Add horizontal spacing
        panel.add(textField3);
        panel.add(Box.createHorizontalStrut(spacing)); // Add horizontal spacing
        panel.add(textField4);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.NORTH);

        getContentPane().add(displayObjects);

        menuPanel.setBackground(new Color(171, 149, 39));
        getContentPane().add(menuPanel, BorderLayout.EAST);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(width + dx, height + dy);
        setVisible(true);
        getContentPane().getComponent(1).requestFocus();
    }

    void setDisplayObjects(ArrayList<GameFigure> objects) {
        getContentPane().remove(1);
        displayObjects = new DisplayObjects(width, height, koef);
        displayObjects.setFigures(objects);
        progressBar.setMaximum(displayObjects.getBrokeKol());
        getContentPane().add(displayObjects, 1);
        getContentPane().getComponent(1).requestFocus();
    }

    void setProgressValue() {
        progressValue++;
        score++;
        game.statusBar.setProgressBar(Integer.toString(progressValue));
        progressBar.setValue(progressValue);
        game.statusBar.setDestroyed(Integer.toString(score));
        textField3.setText("Score: " + score);
    }


    void formatTime(long elapsedTime) {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        Date date = new Date(elapsedTime);
        String time = format.format(date);
        game.statusBar.setTime(time);
        textField4.setText("Time: " + game.statusBar.getTime());
    }

    public void close() {
        getContentPane().removeAll();
        setVisible(false);
        flag = true;
    }

    public void setStatusBarFields(String name, String surname, String destr, String progress, String time) {
        game.statusBar.setName(name);
        game.statusBar.setSurname(surname);
        game.statusBar.setDestroyed(destr);
        game.statusBar.setProgressBar(progress);
        game.statusBar.setTime(time);
    }

    public void updateStatusBar() {
        textField1.setText("Name: " + game.statusBar.getName());
        textField2.setText("Surname: " + game.statusBar.getSurname());
        textField3.setText("Score: " + game.statusBar.getDestroyed());
        textField4.setText("Time: " + game.statusBar.getTime());
        progressBar.setValue(Integer.parseInt(game.statusBar.getProgressBar()));
        progressValue = Integer.parseInt(game.statusBar.getProgressBar());
    }

    public DisplayObjects getDisplayFigures() {
        return displayObjects;
    }

    public void setDisplayFigures(DisplayObjects displayObjects) {
        this.displayObjects = displayObjects;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Event event) {
            score += event.score;
            game.statusBar.setDestroyed(Integer.toString(score));
            textField3.setText("Score: " + score);
        }
    }
}
