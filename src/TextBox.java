import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.*;
import java.lang.reflect.Field;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TextBox extends GameFigure {
    private String text;

    public TextBox(int startX, int startY, int endX, int endY, int X, int Y, int color, int drawAmount, Boolean isStatic, int dx, int dy) {
        super(startX, startY, endX, endY, X, Y, color, drawAmount, isStatic, dx, dy);
    }

    public void drawText() {

    }

    @Override
    boolean figureMove() {
        return false;
    }

    @Override
    void draw(Graphics2D g2d) {
    }
}