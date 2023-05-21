import javax.swing.*;
import java.awt.*;

public class PlayerForm extends JFrame {
    JTextField nameField;
    JTextField surnameField;
    JButton submitButton;

    public PlayerForm() {
        setTitle("Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panel);

        JLabel nameLabel = new JLabel("Input name:");
        panel.add(nameLabel);

        nameField = new JTextField(20);
        panel.add(nameField);

        JLabel surnameLabel = new JLabel("Input surname:");
        panel.add(surnameLabel);

        surnameField = new JTextField(20);
        panel.add(surnameField);

        submitButton = new JButton("Play");
        panel.add(submitButton);

        // Применение стилей к компонентам
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        nameLabel.setFont(labelFont);
        surnameLabel.setFont(labelFont);
        submitButton.setFont(labelFont);

        // Применение цветов
        panel.setBackground(Color.white);
        nameLabel.setForeground(Color.blue);
        surnameLabel.setForeground(Color.blue);

        setVisible(true);
    }
}
