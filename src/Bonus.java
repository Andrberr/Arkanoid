public class Bonus extends DisplayObject {
    private int bonusType;
    private String text;

    public Bonus(int color, int x1, int y1, int x2, int y2, int x, int y, int bonusType) {
        super(color, x1, y1, x2, y2, x, y);
        this.bonusType = bonusType;
    }

    public int getBonusType() {
        return bonusType;
    }

    public void setBonusType(int bonusType) {
        this.bonusType = bonusType;
    }

    @Override
    void checkCollision(DisplayObject object) {

    }

    @Override
    void draw() {

    }

    @Override
    void move() {

    }
}
