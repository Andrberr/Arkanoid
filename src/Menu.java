import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private Button newGameBt;
    private Button finishGameBt;
    private JPanel panel;

    Game game;
    Boolean isPaused = false;

    Container container;

    Menu(Game obj, Container container) {
        game = obj;
        this.container = container;
    }

    void drawMenu() {
        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        JButton newGameButton = new JButton("Start new game");
        JButton pauseGameButton = new JButton("Pause game");
        JButton loadGameButton = new JButton("Load game");
        JButton saveGameButton = new JButton("Save game");
        JButton settingsGameButton = new JButton("Settings");
        JButton exitButton = new JButton("Exit");

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game();
                container.getComponent(0).requestFocus();
            }
        });
        pauseGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.pauseGame(isPaused);
                if (!isPaused) pauseGameButton.setText("Continue game");
                else pauseGameButton.setText("Pause game");
                isPaused = !isPaused;
                pauseGameButton.transferFocus();
                container.getComponent(0).requestFocus();
            }
        });
        settingsGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // game.settings.settingsFrame = new SettingsFrame(game.settings);
            }
        });
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.loadGame();
                container.getComponent(0).requestFocus();
            }
        });

        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.saveGame();
                container.getComponent(0).requestFocus();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Добавляем элементы на панель
        panel.add(newGameButton, BorderLayout.EAST);
        panel.add(pauseGameButton, BorderLayout.EAST);
        panel.add(settingsGameButton, BorderLayout.EAST);
        panel.add(loadGameButton, BorderLayout.EAST);
        panel.add(saveGameButton, BorderLayout.EAST);
        panel.add(exitButton, BorderLayout.EAST);
        container.add(panel, BorderLayout.EAST);
    }

    public void newGameBtClick() {

    }

    public void finishGameBtClick() {

    }

}
