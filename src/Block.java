public class Block extends DisplayObject {
    private Bonuses bonuses;

    public Block(int color, int coordinateX, int coordinateY, int coordinateX1, int coordinateY1, int bindX, int bindY, Bonuses bonuses) {
        super(color, coordinateX, coordinateY, coordinateX1, coordinateY1, bindX, bindY);
        this.bonuses = bonuses;
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
    void draw() {

    }

    @Override
    void move() {

    }
}
