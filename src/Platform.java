import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.lang.reflect.Field;

import static java.lang.Math.abs;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Platform extends GameFigure implements Serializable {
    KeyEvent key;

    public Platform(int startX, int startY, int endX, int endY, int centerX, int centerY, int color, int drawAmount, boolean isStatic, int dx, int dy) {
        super(startX, startY, endX, endY, centerX, centerY, color, drawAmount, isStatic, dx, dy);
    }

    Platform() {

    }

    @Override
    boolean figureMove() {
        if (key != null) {
            int keyCode = key.getKeyCode();
            if (keyCode == KeyEvent.VK_LEFT) {
                setStartX(getStartX() - drawAmount);
                endX = (endX - drawAmount);
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                setStartX(getStartX() + drawAmount);
                endX = (endX + drawAmount);
            }
            if (getStartX() < 0) {
                endX = (endX - getStartX());
                setStartX(0);
            } else if (endX > 1000) {
                setStartX(1000 - (endX - getStartX()));
                endX = 1000;
            }
        }
        return true;
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setColor(new Color(color));
        Rectangle rect = new Rectangle(getStartX(), startY, abs(getStartX() - endX), abs(startY - endY));
        g2d.fill(rect);
        g2d.draw(rect);
    }
}
