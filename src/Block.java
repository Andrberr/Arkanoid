import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import static java.lang.Math.abs;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Block extends DisplayObject implements Serializable {
    private Bonuses bonuses;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    private boolean isAlive;

    public Block(int x1, int y1, int x2, int y2, int x, int y, Bonuses bonuses, int color, boolean isDynamic) {
        super(x1, y1, x2, y2, x, y, color, isDynamic);
        this.bonuses = bonuses;
        this.isAlive = true;
    }

    public Block(){

    }

    public Bonuses getBonuses() {
        return bonuses;
    }

    public void setBonuses(Bonuses bonuses) {
        this.bonuses = bonuses;
    }

    @Override
    void draw(Graphics2D g2d) {
        if (isAlive) {
            g2d.setColor(new Color(getColor()));
            Rectangle rect = new Rectangle(getX1(), getY1(), abs(getX1() - getX2()), abs(getY1() - getY2()));
            g2d.fill(rect);
            g2d.setColor(Color.BLACK);
            g2d.draw(rect);
        }
    }

    @Override
    void move() {

    }

    @Override
    public void serializeToTextFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(getClass().getName());
            writer.println(getX1() + "," + getY1() + "," + getX2() + "," + getY2() + "," + getX() + "," + getY() + "," + getColor() + "," + isAlive());
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
        setAlive(Boolean.parseBoolean(parts[7]));
        setBonuses(null);
        setDynamic(false);
    }
}
