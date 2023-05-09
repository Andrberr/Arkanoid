import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.lang.reflect.Field;

import static java.lang.Math.abs;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Platform extends GameFigure implements Serializable {
    KeyEvent key;

    public Platform(int startX, int startY, int endX, int endY, int centerX, int centerY, int color, int drawAmount, boolean isStatic, int dx, int dy) {
        super(startX, startY, endX, endY, centerX, centerY, color, drawAmount, isStatic, dx, dy);
    }

    Platform() {

    }

    @Override
    boolean figureMove() {
        if (key != null) {
            int keyCode = key.getKeyCode();
            if (keyCode == KeyEvent.VK_LEFT) {
                setStartX(getStartX() - drawAmount);
                endX = (endX - drawAmount);
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                setStartX(getStartX() + drawAmount);
                endX = (endX + drawAmount);
            }
            if (getStartX() < 0) {
                endX = (endX - getStartX());
                setStartX(0);
            } else if (endX > 1000) {
                setStartX(1000 - (endX - getStartX()));
                endX = 1000;
            }
        }
        return true;
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setColor(new Color(color));
        Rectangle rect = new Rectangle(getStartX(), startY, abs(getStartX() - endX), abs(startY - endY));
        g2d.fill(rect);
        g2d.draw(rect);
    }

    @Override
    Object createFieldObject() throws IllegalAccessException, NoSuchFieldException {
        Field field = this.getClass().getSuperclass().getDeclaredField("startX");
        field.setAccessible(true);
        field.set(this, this.getStartX());
        field = super.getClass().getSuperclass().getDeclaredField("startY");
        field.setAccessible(true);
        field.set(this, this.startY);
        field = this.getClass().getSuperclass().getDeclaredField("endX");
        field.setAccessible(true);
        field.set(this, this.endX);
        field = this.getClass().getSuperclass().getDeclaredField("endY");
        field.setAccessible(true);
        field.set(this, this.endY);
        field = this.getClass().getSuperclass().getDeclaredField("X");
        field.setAccessible(true);
        field.set(this, this.X);
        field = this.getClass().getSuperclass().getDeclaredField("Y");
        field.setAccessible(true);
        field.set(this, this.Y);
        field = this.getClass().getSuperclass().getDeclaredField("color");
        field.setAccessible(true);
        field.set(this, this.color);
        field = this.getClass().getSuperclass().getDeclaredField("drawAmount");
        field.setAccessible(true);
        field.set(this, this.drawAmount);
        field = this.getClass().getSuperclass().getDeclaredField("dx");
        field.setAccessible(true);
        field.set(this, this.dx);
        field = this.getClass().getSuperclass().getDeclaredField("dy");
        field.setAccessible(true);
        field.set(this, this.dy);
        field = this.getClass().getSuperclass().getDeclaredField("isStatic");
        field.setAccessible(true);
        field.set(this, this.isStatic);
        return this;
    }

    @Override
    public void deserializeFromField() throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getSuperclass().getDeclaredField("startX");
        field.setAccessible(true);
        setStartX((Integer) field.get(this));
        field = super.getClass().getSuperclass().getDeclaredField("startY");
        field.setAccessible(true);
        startY = (int) field.get(this);
        field = this.getClass().getSuperclass().getDeclaredField("endX");
        field.setAccessible(true);
        endX = (int) field.get(this);
        field = this.getClass().getSuperclass().getDeclaredField("endY");
        field.setAccessible(true);
        endY = (int) field.get(this);
        field = this.getClass().getSuperclass().getDeclaredField("X");
        field.setAccessible(true);
        X = (int) field.get(this);
        field = this.getClass().getSuperclass().getDeclaredField("Y");
        field.setAccessible(true);
        Y = (int) field.get(this);
        field = this.getClass().getSuperclass().getDeclaredField("color");
        field.setAccessible(true);
        color = (int) field.get(this);
        field = this.getClass().getSuperclass().getDeclaredField("drawAmount");
        field.setAccessible(true);
        drawAmount = (int) field.get(this);
        field = this.getClass().getSuperclass().getDeclaredField("dx");
        field.setAccessible(true);
        dx = (int) field.get(this);
        field = this.getClass().getSuperclass().getDeclaredField("dy");
        field.setAccessible(true);
        dy = (int) field.get(this);
        field = this.getClass().getSuperclass().getDeclaredField("isStatic");
        field.setAccessible(true);
        isStatic = (Boolean) field.get(this);
    }

}
