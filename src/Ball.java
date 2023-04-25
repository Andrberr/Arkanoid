import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import static java.lang.Math.abs;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ball extends DisplayObject implements Serializable {
    private int speed;
    private int radius;

    public Ball(int x1, int y1, int x2, int y2, int x, int y, int speed, int radius, int color, boolean isDynamic) {
        super(x1, y1, x2, y2, x, y, color, isDynamic);
        this.speed = speed;
        this.radius = radius;
        setDirectionX(-1);
        setDirectionY(-1);
    }

    public Ball() {

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


    @Override
    void draw(Graphics2D g2d) {
        g2d.setColor(new Color(getColor()));
        g2d.fillOval(getX1(), getY1(), abs(getX1() - getX2()), abs(getY1() - getY2()));
    }

    @Override
    void move() {
        if (getX1() <= 0 || getX2() >= 1170) setDirectionX(-getDirectionX());
        if (getY1() <= 0) setDirectionY(-getDirectionY());
        double dx = getDirectionX() * getSpeed() * Math.cos(Math.PI / 5) * 2;
        double dy = getDirectionY() * getSpeed() * Math.sin(Math.PI / 5) * 2;
        setX1(getX1() + (int) dx);
        setX2(getX2() + (int) dx);
        setX(getX() + (int) dx);
        setY1(getY1() + (int) dy);
        setY2(getY2() + (int) dy);
        setY(getY() + (int) dy);
    }

    @Override
    public void serializeToTextFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(getClass().getName());
            writer.println(getX1() + "," + getY1() + "," + getX2() + "," + getY2() + "," + getX() + "," + getY() + "," + getColor() + "," + speed + "," + radius + "," + getDirectionX() + "," + getDirectionY());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deserializeFromTextFile(String content) {
        String[] parts = content.split(",");
        setX1(Integer.parseInt(parts[0]));
        setY1(Integer.parseInt(parts[1]));
        setX2(Integer.parseInt(parts[2]));
        setY2(Integer.parseInt(parts[3]));
        setX(Integer.parseInt(parts[4]));
        setY(Integer.parseInt(parts[5]));
        setColor(Integer.parseInt(parts[6]));
        speed = Integer.parseInt(parts[7]);
        radius = Integer.parseInt(parts[8]);
        setDirectionX(Integer.parseInt(parts[9]));
        setDirectionY(Integer.parseInt(parts[10]));
        setDynamic(true);
    }
}
