import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DisplayCollection extends JPanel {
    private DisplayObject[] objects;
    private int width;
    private int height;

    public DisplayCollection(int width, int height){
        objects = new DisplayObject[47];
        this.height = height;
        this.width = width;
        addObjects();
        setFocusable(true);
        requestFocusInWindow();
    }

    public DisplayObject[] getObjects() {
        return objects;
    }

    public void setObjects(DisplayObject[] objects) {
        this.objects = objects;
    }

    void addObjects(){
        int index = 0;
        Blocks blocks = new Blocks();
        for (int i = 0; i < blocks.getBlocks().length; i++) {
            objects[index++] = blocks.getBlocks()[i];
        }

        Platform platform = new Platform(width / 2 - 80, height-60, width / 2 + 80, height-40, width / 2, height - 20, 5, new Color(150, 34, 154).getRGB(), true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                platform.key = e;
            }
        });
        objects[index++] = platform;

        Balls balls = new Balls();
        for (int i=0; i<balls.getBalls().length; i++){
            objects[index++] = balls.getBalls()[i];
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(171, 149, 39));
        Graphics2D g2d = (Graphics2D) g;
        for (DisplayObject object:objects) {
            object.draw(g2d);
        }
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(5));
        Rectangle rect = new Rectangle(0,0, width, height-20);
        g2d.draw(rect);
    }

    public void removeObjects(DisplayObject displayObject[])
    {

    }
}
