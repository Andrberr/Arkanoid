import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class DisplayObjects extends JPanel {
    private ArrayList<GameFigure> figures;
    Platform currentDesk;
    int currentIndex = 0;

    public DisplayObjects() {
//        int figuresAmount = 74;
//        figures = new GameFigure[figuresAmount];
        figures = new ArrayList<GameFigure>();
        Blocks blocks = new Blocks();
        figures.addAll(blocks.getGameBlocks());
        addKey();
        Platforms desks = new Platforms();
        currentDesk = desks.getDesk(0);
        figures.addAll(desks.getBallDesks());
        Balls balls = new Balls();
        figures.addAll(balls.getGameBalls());
        setFocusable(true);
        requestFocusInWindow();
    }

    public void addKey(){
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                currentDesk.key = e;
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(171, 149, 39));
        Graphics2D g2d = (Graphics2D) g;
        for (GameFigure figure : figures) {
            figure.draw(g2d);
        }
    }

    public void addFigure(ArrayList<GameFigure> figures) {
        this.figures.addAll(figures);
    }
    public void removeFigure(GameFigure figures[]) {
    }

    public ArrayList<GameFigure> getFigures() {
        return figures;
    }
}
