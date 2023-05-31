import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.Random;

import static java.lang.Math.abs;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bonus extends GameFigure {
    private int type;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Bonus(int startX, int startY, int endX, int endY, int centerX, int centerY, int color, int drawAmount, Boolean isStatic, int dx, int dy, int height) {
        super(startX, startY, endX, endY, centerX, centerY, color, drawAmount, isStatic, dx, dy, height);
        Random random = new Random();
        type = random.nextInt(5) + 1;
        if (type == 3) score = random.nextInt(10) + 1;
        else score = 0;
    }

    public Bonus() {

    }

    @Override
    boolean figureMove() {
        if (endY > height) return false;
        startY += dy;
        endY += dy;
        Y += dy;
        return true;
    }

    @Override
    void draw(Graphics2D g2d) {
        if (endY > height) return;
        g2d.setColor(new Color(color));
        Rectangle rect = new Rectangle(getStartX(), startY, abs(getStartX() - endX), abs(startY - endY));
        g2d.fill(rect);
        g2d.setColor(Color.BLACK);
        g2d.draw(rect);
        String text = "";
        switch (type) {
            case 1 -> {
                text = "Size+";
            }
            case 2 -> {
                text = "Invis";
            }
            case 3 -> {
                text = "+" + score;
            }
            case 4 -> {
                text = "Speed";
            }
            case 5 -> {
                text = "Gun";
            }
        }
        Font font = new Font("Arial", Font.BOLD, 14);
        g2d.setFont(font);
        g2d.drawString(text, getStartX() + (getX() - getStartX()) / 2, getY());
    }
}
