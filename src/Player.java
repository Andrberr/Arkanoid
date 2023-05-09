public class Player {
    private String name;
    private PlayerStatistic playerStatistic;
    private Balls balls;
    private Platforms platforms;

    public Player(String name, PlayerStatistic playerStatistic, Balls balls, Platforms platforms) {
        this.name = name;
        this.playerStatistic = playerStatistic;
        this.balls = balls;
        this.platforms = platforms;
    }

    public boolean isPlayerWin(){
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerStatistic getGamePlayStatistic() {
        return playerStatistic;
    }

    public void setGamePlayStatistic(PlayerStatistic playerStatistic) {
        this.playerStatistic = playerStatistic;
    }

    public Balls getGameBalls() {
        return balls;
    }

    public void setGameBalls(Balls balls) {
        this.balls = balls;
    }

    public Platforms getBallDesks() {
        return platforms;
    }

    public void setBallDesks(Platforms platforms) {
        this.platforms = platforms;
    }
}
