import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame {

    private final Settings settings;
    private final JSlider volumeSlider;
    private final JSlider brightnessSlider;
    private final JComboBox<String> difficultyComboBox;

    public SettingsFrame(Settings settings) {
        super("Настройки");
        this.settings = settings;
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, settings.getVolume());
        volumeSlider.setMajorTickSpacing(20);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);

        brightnessSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, settings.getBrightness());
        brightnessSlider.setMajorTickSpacing(20);
        brightnessSlider.setMinorTickSpacing(5);
        brightnessSlider.setPaintTicks(true);
        brightnessSlider.setPaintLabels(true);

        difficultyComboBox = new JComboBox<>(new String[]{"Лёгкий", "Средний", "Сложный"});
        difficultyComboBox.setSelectedIndex(settings.getDifficulty());

        JButton applyButton = new JButton("Принять");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVolume(volumeSlider.getValue());
                settings.setBrightness(brightnessSlider.getValue());
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
        panel.add(new JLabel("Громкость"), c);
        c.gridx = 1;
        panel.add(volumeSlider, c);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JLabel("Яркость"), c);
        c.gridx = 1;
        panel.add(brightnessSlider, c);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(new JLabel("Сложность"), c);
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
