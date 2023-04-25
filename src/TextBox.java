import java.awt.*;
import java.io.Serializable;

public class TextBox extends DisplayObject implements Serializable {
    private String text;

    public TextBox(int coordinateX, int coordinateY, int coordinateX1, int coordinateY1, int bindX, int bindY, int color, boolean isDynamic) {
        super(coordinateX, coordinateY, coordinateX1, coordinateY1, bindX, bindY, color, isDynamic);
    }

    public void drawText(){

    }

    @Override
    void draw(Graphics2D g2d) {

    }

    @Override
    void move() {

    }

    @Override
    public void serializeToTextFile(String filename) {

    }

    @Override
    public void deserializeFromTextFile(String filename) {

    }
}
