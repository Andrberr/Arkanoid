import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Math.abs;

public class Platform extends DisplayObject {
    private int speed;
    private KeyEvent key;

    public Platform(int x1, int y1, int x2, int y2, int x, int y, int speed, Color color, boolean isDynamic) {
        super(x1, y1, x2, y2, x, y, color, isDynamic);
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setKey(KeyEvent key) {
        this.key = key;
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setColor(getColor());
        Rectangle rect = new Rectangle(getX1(), getY1(), abs(getX1() - getX2()), abs(getY1() - getY2()));
        g2d.fill(rect);
        g2d.setColor(Color.BLACK);
        g2d.draw(rect);
    }

    @Override
    public void move() {
        if (key != null) {
            int keyCode = key.getKeyCode();
            if (keyCode == KeyEvent.VK_LEFT) {
                // Двигаем платформу влево
                setX1(getX1() - speed);
                setX2(getX2() - speed);
                setX(getX() - speed);
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                // Двигаем платформу вправо
                setX1(getX1() + speed);
                setX2(getX2() + speed);
                setX(getX() + speed);
            }
            // Проверяем, не вышла ли платформа за границы экрана
            if (getX1() <= 0) {
                setX2(getX2() - getX1());
                setX1(0);
                setX((getX2() - getX1()) / 2);
            } else if (getX2() >= 1170) {
                setX1(1170 - (getX2() - getX1()));
                setX2(1170);
                setX(1170 - (getX2() - getX1())/2);
            }
        }
    }
}
