import java.awt.*;

import static java.lang.Math.abs;

public class Block extends DisplayObject {
    private Bonuses bonuses;

    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    private boolean isAlive;

    public Block(int x1, int y1, int x2, int y2, int x, int y, Bonuses bonuses, Color color, boolean isDynamic) {
        super(x1, y1, x2, y2, x, y, color, isDynamic);
        this.bonuses = bonuses;
        this.isAlive = true;
    }

    public Bonuses getBonuses() {
        return bonuses;
    }

    public void setBonuses(Bonuses bonuses) {
        this.bonuses = bonuses;
    }

    @Override
    void checkCollision(DisplayObject object) {

    }

    @Override
    void draw(Graphics2D g2d) {
        if (isAlive) {
            g2d.setColor(getColor());
            Rectangle rect = new Rectangle(getX1(), getY1(), abs(getX1() - getX2()), abs(getY1() - getY2()));
            g2d.fill(rect);
            g2d.setColor(Color.BLACK);
            g2d.draw(rect);
        }
    }

    @Override
    void move() {

    }
}
