import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame {

    private final Settings settings;
    private final JComboBox<String> difficultyComboBox;

    private final JComboBox<String> screenComboBox;

    public SettingsFrame(Settings settings, Game game) {
        super("Настройки");
        this.settings = settings;

        difficultyComboBox = new JComboBox<>(new String[]{"Лёгкий", "Средний", "Сложный"});
        difficultyComboBox.setSelectedIndex(settings.getDifficulty());

        screenComboBox = new JComboBox<>(new String[]{"2560x1600", "1920x1080", "Сложный"});
        screenComboBox.setSelectedIndex(settings.getDifficulty());

        JButton applyButton = new JButton("Принять");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setScreen(screenComboBox.getItemAt(screenComboBox.getSelectedIndex()));
                if (screenComboBox.getSelectedIndex() == 1) {
                    try {
                        game.startNewGame(770, 480);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                settings.setDifficulty(difficultyComboBox.getSelectedIndex());
                if (Game.gameField !=null)
                    for (GameFigure figure: Game.gameField.displayObjects.getFigures()) {
                        if (figure instanceof Ball)
                            ((Ball) figure).setSpeed(difficultyComboBox.getSelectedIndex() + 2);
                    }
                dispose();
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(new JLabel("Screen size"), c);
        c.gridx = 1;
       panel.add(screenComboBox, c);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(new JLabel("Difficulty"), c);
        c.gridx = 1;
        panel.add(difficultyComboBox, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        panel.add(applyButton, c);

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
