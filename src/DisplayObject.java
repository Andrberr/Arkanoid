import java.awt.*;

public abstract class DisplayObject {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int x;
    private int y;
    private Color color;
    private boolean isDynamic;
    private int directionX;
    private int directionY;

    public DisplayObject(int x1, int y1, int x2, int y2, int x, int y, Color color, boolean isDynamic) {
        this.color = color;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x = x;
        this.y = y;
        this.isDynamic = isDynamic;
        this.directionX = 1;
        this.directionY = 1;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public boolean isDynamic() {
        return isDynamic;
    }

    public int getDirectionX() {
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

    void checkCollision(DisplayObject object) {
        int x1 = this.x1;
        int x2 = this.x2;
        int y1 = this.y1;
        int y2 = this.y2;
        int x3 = object.x1;
        int x4 = object.x2;
        int y3 = object.y1;
        int y4 = object.y2;

        boolean isBottomCollision = y2 >= y3 && y2 <= y4 && x1 <= x4 && x2 >= x3;
        if (object instanceof Platform) {
            if (isBottomCollision) {
                directionY = -directionY;
            }
        } else if (object instanceof Block) {
            if (((Block) object).isAlive()) {
                if (isBottomCollision || (y1 >= y3 && y1 <= y4 && x1 <= x4 && x2 >= x3)) {
                    directionY = -directionY;
                    ((Block) object).setAlive(false);
                } else
                if ((y1 <= y4 && y2 >= y3 && x2 >= x3 && x2 <= x4) || (y1 <= y4 && y2 >= y3 && x1 >= x3 && x1 <= x4)) {
                    directionX = -directionX;
                    ((Block) object).setAlive(false);
                }
            }
        }
    }

    abstract void draw(Graphics2D g2d);

    abstract void move();
}
