import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import static java.lang.Math.abs;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ball extends GameFigure implements Observer {

    private int width;
    private Boolean fourthBonus;

    public Boolean getFourthBonus() {
        return fourthBonus;
    }

    public void setFourthBonus(Boolean fourthBonus) {
        this.fourthBonus = fourthBonus;
    }


    public Ball(int startX, int startY, int endX, int endY, int centerX, int centerY, int color, int drawAmount, Boolean isStatic, int dx, int dy, int width, int height) {
        super(startX, startY, endX, endY, centerX, centerY, color, drawAmount, isStatic, dx, dy, height);
        this.width = width;
        fourthBonus = false;
    }

    Ball() {

    }

    @Override
    boolean figureMove() {
        if (endY > height - 1) {
            Game.timer.stop();
            
            return false;
        }
        if (startY < 3) dy = -dy;
        if (getStartX() < 0 || endX > width) dx = -dx;
        setStartX(getStartX() + dx);
        endX = endX + dx;
        startY = startY - dy;
        endY = endY - dy;
        if (dx != 0) dx = drawAmount * dx / abs(dx);
        if (dy != 0) dy = drawAmount * dy / abs(dy);
        return true;
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setColor(new Color(color));
        g2d.fillOval(getStartX() - 1, startY - 1, abs(getStartX() - endX - 1), abs(startY - endY - 1));
    }

    public void setSpeed(int speed) {
        drawAmount = speed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Event event) {
            if (event.bonusType == 4 && !fourthBonus) {
                fourthBonus = true;
                drawAmount++;
                Thread myThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        long currTime;
                        while (true) {
                            currTime = System.currentTimeMillis();
                            if (currTime >= time + 3000L) {
                                fourthBonus = false;
                                drawAmount--;
                                break;
                            }
                        }
                    }
                });

                myThread.start();
            }
        }
    }
}
