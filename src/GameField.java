import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameField extends JFrame {
    DisplayObjects displayObjects;
    GameStatistic gameStatistic;
    int background;
    GameMessageBox gameMessageBox;
    Game game;

    int width;
    int height;

    double koef = 1;
    boolean flag = true;

    int progressValue = 0;


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

        switch (width) {
            case 1100:{
                koef = 1;
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                break;
            }
            case 770: {
                koef = 0.7;
                break;
            }
        }

        setTitle("Arkanoid");

        //Menu
        JPanel menuPanel = new JPanel(new GridLayout(5, 1, 0, 5));
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
                    getContentPane().getComponent(1).requestFocus();

                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
                            long time = System.currentTimeMillis();
                            while (System.currentTimeMillis() < time + 10) {
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
                Game.timer.stop();
                System.exit(0);
            }
        });


        //ProgressBar

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setValue(0);

        button = new JButton("Menu");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!resumeGameButton.isVisible() && !flag) {
                    Game.timer.stop();

                }
                resumeGameButton.setVisible(!resumeGameButton.isVisible());
                settingsGameButton.setVisible(!settingsGameButton.isVisible());
                loadGameButton.setVisible(!loadGameButton.isVisible());
                saveGameButton.setVisible(!saveGameButton.isVisible());
                exitButton.setVisible(!exitButton.isVisible());
                flag = false;
            }
        });

        textField1 = new JTextField();
        textField1.setEditable(false);
        textField1.setText("Name: Andrey");
        textField2 = new JTextField();
        textField2.setEditable(false);
        textField2.setText("Surname: Beryozkin");
        textField3 = new JTextField();
        textField3.setEditable(false);
        textField3.setText("Score: 0");
        textField4 = new JTextField();
        textField4.setEditable(false);
        textField4.setText("Time: 00:00");
        Font font = new Font(textField1.getFont().getName(), Font.PLAIN, 18);
        textField1.setFont(font);
        font = new Font(textField2.getFont().getName(), Font.PLAIN, 18);
        textField2.setFont(font);
        font = new Font(textField3.getFont().getName(), Font.PLAIN, 18);
        textField3.setFont(font);
        font = new Font(textField4.getFont().getName(), Font.PLAIN, 18);
        textField4.setFont(font);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(progressBar);
        panel.add(Box.createHorizontalStrut(30)); // Add horizontal spacing
        panel.add(button);
        panel.add(Box.createHorizontalStrut(30)); // Add horizontal spacing
        panel.add(textField1);
        panel.add(Box.createHorizontalStrut(30)); // Add horizontal spacing
        panel.add(textField2);
        panel.add(Box.createHorizontalStrut(30)); // Add horizontal spacing
        panel.add(textField3);
        panel.add(Box.createHorizontalStrut(30)); // Add horizontal spacing
        panel.add(textField4);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.NORTH);

        //Objects
        displayObjects = new DisplayObjects(width, height, koef);
        getContentPane().add(displayObjects);

        menuPanel.setBackground(new Color(171, 149, 39));
        getContentPane().add(menuPanel, BorderLayout.EAST);

        getContentPane().getComponent(1).requestFocus();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setVisible(true);
    }

    void setProgressValue() {
        progressValue += 2;
        progressBar.setValue(progressValue);

        textField3.setText("Score: " + progressValue / 2);
    }

    void formatTime(long elapsedTime) {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        Date date = new Date(elapsedTime);
        String time = format.format(date);
        textField4.setText("Time: " + time);
    }


    public DisplayObjects getDisplayFigures() {
        return displayObjects;
    }

    public void setDisplayFigures(DisplayObjects displayObjects) {
        this.displayObjects = displayObjects;
    }

    public void close() {
        setVisible(false);
    }
}
