import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.*;
import java.lang.reflect.Field;

import static java.lang.Math.abs;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Block extends GameFigure {
    private Boolean isHitted = false;

    public Block(int startX, int startY, int endX, int endY, int X, int Y, int color, int drawAmount, Boolean isStatic, int dx, int dy) {
        super(startX, startY, endX, endY, X, Y, color, drawAmount, isStatic, dx, dy);
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

    public void setHitted(Boolean hitted) {
        isHitted = hitted;
    }

    @Override
    public Object createFieldObject() throws IllegalAccessException, NoSuchFieldException {
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
        field = this.getClass().getDeclaredField("isHitted");
        field.setAccessible(true);
        field.set(this, this.isHitted);
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
        field = this.getClass().getDeclaredField("isHitted");
        field.setAccessible(true);
        isHitted = (Boolean) field.get(this);
    }
}
