import java.awt.*;
import java.util.Random;

import static java.lang.Math.random;

public abstract class GameFigure {
    private int startX;
    int startY;
    int endX;
    int endY;
    int X;
    int Y;
    int color;
    int drawAmount;
    Boolean isStatic;
    int dx;
    int dy;

    public GameFigure(int startX, int startY, int endX, int endY, int X, int Y, int color, int drawAmount, boolean isStatic, int dx, int dy) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.X = X;
        this.Y = Y;
        this.color = color;
        this.drawAmount = drawAmount;
        this.isStatic = isStatic;
        this.dx = dx;
        this.dy = dy;
    }

    public GameFigure() {

    }

    abstract boolean figureMove();

    public Boolean isCollides(GameFigure gameFigure) {
        int x1 = startX;
        int x2 = endX;
        int y1 = startY;
        int y2 = endY;
        int x3 = gameFigure.startX;
        int x4 = gameFigure.endX;
        int y3 = gameFigure.startY;
        int y4 = gameFigure.endY;
        boolean withBlock = false;
        boolean res = (x2 >= x3) && (x1 <= x4) && (y2 >= y3) && (y1 <= y4);
        if (res) {
            int k;
            if (getRand() == 0) k = 1;
            else k = -1;
            dx = (dx + (int) (random() * 5)) * k;
            dy = (dy) * (-1);
            if (gameFigure.getClass().toString().equals("class Block")) {
                Block block = (Block) gameFigure;
                block.setHitted(true);
                withBlock = true;
            }
        }
        return withBlock;
    }

    abstract void draw(Graphics2D g2d);

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getDrawAmount() {
        return drawAmount;
    }

    public void setDrawAmount(int drawAmount) {
        this.drawAmount = drawAmount;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Boolean getStatic() {
        return isStatic;
    }

    public void setStatic(Boolean isStatic) {
        this.isStatic = isStatic;
    }

    private int getRand() {
        Random random = new Random();
        return random.nextInt(2);
    }
}
