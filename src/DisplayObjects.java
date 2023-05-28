import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class DisplayObjects extends JPanel {
    private ArrayList<GameFigure> figures;
    Platform currentDesk;
    double koef;
    int width;
    int height;

    private Blocks blocks;

    static EventSource eventSource;



    public DisplayObjects(int width, int height, double koef) {
        this.width = width;
        this.height = height;
        this.koef = koef;
        eventSource = new EventSource();

        figures = new ArrayList<GameFigure>();
        Blocks blocks = new Blocks(koef, height);
        this.blocks = blocks;
        figures.addAll(blocks.getGameBlocks());
        addKey();
        Platforms desks = new Platforms(koef, width, height);
        currentDesk = desks.getDesk(0);
        figures.addAll(desks.getBallDesks());
        Balls balls = new Balls(koef, width, height);
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
            if (figure instanceof Block fig){
                if (fig.getHitted() && fig.getBonus() != null){
                    fig.getBonus().figureMove();
                    fig.getBonus().draw(g2d);
                }
            }
        }
        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, height, width, height);
        g2d.drawLine(width, 0, width, height);
    }

    public int getBrokeKol(){
        return blocks.getBrokeKol();
    }

    public ArrayList<GameFigure> getFigures() {
        return figures;
    }
    public void setFigures(ArrayList<GameFigure> figures) {
        this.figures = figures;
    }
}
