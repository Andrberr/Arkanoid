public class Bonus extends GameObject {
    private int bonusType;

    public Bonus(String color, float coordinateX, float coordinateY, float width, float height, int bonusType) {
        super(color, coordinateX, coordinateY, width, height);
        this.bonusType = bonusType;
    }

    public int getBonusType() {
        return bonusType;
    }

    public void setBonusType(int bonusType) {
        this.bonusType = bonusType;
    }

    @Override
    void checkCollision(GameObject object) {

    }

    @Override
    void draw() {

    }
}
