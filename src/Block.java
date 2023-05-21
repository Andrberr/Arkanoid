import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.*;
import java.lang.reflect.Field;

import static java.lang.Math.abs;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Block extends GameFigure {
    Boolean isHitted = false;

    public Block(int startX, int startY, int endX, int endY, int X, int Y, int color, int drawAmount, Boolean isStatic, int dx, int dy) {
        super(startX, startY, endX, endY, X, Y, color, drawAmount, isStatic, dx, dy);
    }

    Block() {

    }


    @Override
    boolean figureMove() {
        return false;
    }

    @Override
    void draw(Graphics2D g2d) {
        if (!isHitted) {
            g2d.setColor(new Color(color));
            Rectangle rect = new Rectangle(getStartX(), startY, abs(getStartX() - endX), abs(startY - endY));
            g2d.fill(rect);
            g2d.setColor(Color.BLACK);
            g2d.draw(rect);
        } else {
            setStartX(0);
            endX = 0;
            startY = 0;
            endY = 0;
        }
    }

    public void setHitted(Boolean hitted) {
        isHitted = hitted;
    }

}
