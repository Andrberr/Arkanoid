public class Bonus extends DisplayObject {
    private int bonusType;

    public Bonus(int color, int coordinateX, int coordinateY, int coordinateX1, int coordinateY1, int bindX, int bindY, int bonusType) {
        super(color, coordinateX, coordinateY, coordinateX1, coordinateY1, bindX, bindY);
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
