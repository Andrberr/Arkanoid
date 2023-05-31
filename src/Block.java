import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.Random;

import static java.lang.Math.abs;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Block extends GameFigure {
    Boolean isHitted = false;
    private Boolean isBroke = false;

    private Bonus bonus = null;

    transient private final Random random = new Random();

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    public Boolean getHitted() {
        return isHitted;
    }

    public void setHitted(Boolean hitted) {
        isHitted = hitted;
    }


    public Boolean getBroke() {
        return isBroke;
    }

    public void setBroke(Boolean broke) {
        isBroke = broke;
    }

    public Block(int startX, int startY, int endX, int endY, int X, int Y, int color, int drawAmount, Boolean isStatic, int dx, int dy, int height) {
        super(startX, startY, endX, endY, X, Y, color, drawAmount, isStatic, dx, dy, height);
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

    void setBonus(int height) {
        if (!isBroke) return;
        int k = random.nextInt(2);
        if (k == 0) {
            bonus = new Bonus(getStartX(), getStartY(), getStartX() + 60, getStartY() + 50, getStartX() + 30, getStartY() + 25, new Color(54, 213, 166).getRGB(), 1, false, 0, 5, height);
        }
    }
}
