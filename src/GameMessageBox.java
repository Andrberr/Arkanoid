import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class GameMessageBox extends JFrame implements Observer{
    private void showCustomMessageBox() {
        Font font = new Font("Arial", Font.BOLD, 40);
        JLabel messageLabel = new JLabel("Game Over!!!");
        messageLabel.setFont(font);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.timer.stop();
                getContentPane().setVisible(false);
                getContentPane().removeAll();
            }
        });
        getContentPane().setBackground(new Color(114, 103, 57));
        getContentPane().add(messageLabel, BorderLayout.CENTER);
        getContentPane().add(okButton, BorderLayout.SOUTH);
        getContentPane().setPreferredSize(new Dimension(350, 200));
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Event event){
            if (event.type == 6){
                showCustomMessageBox();
            }
        }
    }
}