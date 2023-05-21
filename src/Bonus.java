import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.*;
import java.lang.reflect.Field;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bonus extends GameFigure {
    private int amount;

    public Bonus(int startX, int startY, int endX, int endY, int centerX, int centerY, int color, int drawAmount, Boolean isStatic, int dx, int dy) {
        super(startX, startY, endX, endY, centerX, centerY, color, drawAmount, isStatic, dx, dy);
    }

    Bonus() {

    }

    @Override
    boolean figureMove() {
        return false;
    }

    @Override
    void draw(Graphics2D g2d) {

    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
