import java.awt.*;

import static java.lang.Math.abs;

public class Ball extends DisplayObject {
    private int speed;
    private int radius;
    private int directionX;
    private int directionY;
    private boolean isFinish;

    public Ball(int x1, int y1, int x2, int y2, int x, int y, int speed, int radius, Color color, boolean isDynamic) {
        super(x1, y1, x2, y2, x, y, color, isDynamic);
        this.speed = speed;
        this.radius = radius;
        this.directionX = -1;
        this.directionY = -1;
        this.isFinish = false;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isFinish() {
        return isFinish;
    }

    @Override
    void checkCollision(DisplayObject object) {
        if (object instanceof Platform) {
            if (getX() >= object.getX1() && getX() <= object.getX2() && getY2() >= object.getY1() && getY2() <= object.getY2()) {
                directionY = -directionY;
            }
        } else if (object instanceof Block) {
            if (((Block) object).isAlive()) {
                if (getX() >= object.getX1() && getX() <= object.getX2() &&
                        ((getY1() >= object.getY1() && getY1() <= object.getY2()) || (getY2() >= object.getY1() && getY2() <= object.getY2()))) {
                    directionY = -directionY;
                    ((Block) object).setAlive(false);
                }
                if (getY() >= object.getY1() && getY() <= object.getY2() &&
                        ((getX1() >= object.getX1() && getX1() <= object.getX2()) || (getX2() >= object.getX1() && getX2() <= object.getX2()))) {
                    directionX = -directionX;
                    ((Block) object).setAlive(false);
                }
            }
        }
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setColor(getColor());
        g2d.fillOval(getX1(), getY1(), abs(getX1() - getX2()), abs(getY1() - getY2()));
    }

    @Override
    void move() {
        if (getY2() >= 700) isFinish = true;
        else {
            if (getX1() <= 0 || getX2() >= 1170) directionX = -directionX;
            if (getY1() <= 0) directionY = -directionY;
            double dx = directionX * getSpeed() * Math.cos(Math.PI / 5) * 2;
            double dy = directionY * getSpeed() * Math.sin(Math.PI / 5) * 2;
            setX1(getX1() + (int) dx);
            setX2(getX2() + (int) dx);
            setX(getX() + (int) dx);
            setY1(getY1() + (int) dy);
            setY2(getY2() + (int) dy);
            setY(getY() + (int) dy);
        }
    }
}
