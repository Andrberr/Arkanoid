import java.awt.*;
import java.io.Serializable;

public class Bonus extends DisplayObject implements Serializable {
    private int bonusType;
    private String text;

    public Bonus(int x1, int y1, int x2, int y2, int x, int y, int bonusType, int color, boolean isDynamic) {
        super(x1, y1, x2, y2, x, y, color, true);
        this.bonusType = bonusType;
    }

    public int getBonusType() {
        return bonusType;
    }

    public void setBonusType(int bonusType) {
        this.bonusType = bonusType;
    }

    @Override
    void draw(Graphics2D g2d) {

    }

    @Override
    void move() {

    }

    @Override
    public void serializeToTextFile(String filename) {

    }

    @Override
    public void deserializeFromTextFile(String filename) {

    }
}
