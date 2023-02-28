public class Block extends GameObject {
    private Bonus bonus;

    public Block(String color, float coordinateX, float coordinateY, float width, float height, Bonus bonus) {
        super(color, coordinateX, coordinateY, width, height);
        this.bonus = bonus;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    @Override
    void checkCollision(GameObject object) {

    }

    @Override
    void draw() {

    }
}
