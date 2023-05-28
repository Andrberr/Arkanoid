import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.*;
import java.lang.reflect.Field;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TextBox {
    private String text;

    public TextBox(int startX, int startY, int endX, int endY, int X, int Y, int color, int drawAmount, Boolean isStatic, int dx, int dy) {
    }

    public void drawText() {

    }
}