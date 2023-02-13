public class GameField {
    private float width;
    private float height;
    private Ball ball;
    private Platform platform;
    private DestroyedBlock[] destroyedBlocks;
    private PlayerStatistic playerStatistic;

    public GameField(float width, float height, Ball ball, Platform platform, DestroyedBlock[] destroyedBlocks, PlayerStatistic playerStatistic) {
        this.width = width;
        this.height = height;
        this.ball = ball;
        this.platform = platform;
        this.destroyedBlocks = destroyedBlocks;
        this.playerStatistic = playerStatistic;
    }

    void destroyBlock() {

    }

    void checkForBlockDestruction() {

    }

    boolean checkForEndOfGame() {
        return false;
    }

    void drawGameField() {

    }
}
