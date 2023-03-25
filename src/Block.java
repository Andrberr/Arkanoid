public class Block extends DisplayObject {
    private Bonuses bonuses;

    public Block(int color, int x1, int y1, int x2, int y2, int x, int y, Bonuses bonuses) {
        super(color, x1, y1, x2, y2, x, y);
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
