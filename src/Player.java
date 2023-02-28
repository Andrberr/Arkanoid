public class Player {
    private String name;
    private Balls balls;
    private Platform platform;
    private PlayerStatistic playerStatistic;

    public Player(String name, Balls balls, Platform platform, PlayerStatistic playerStatistic) {
        this.name = name;
        this.balls = balls;
        this.platform = platform;
        this.playerStatistic = playerStatistic;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Balls getBalls() {
        return balls;
    }

    public void setBalls(Balls balls) {
        this.balls = balls;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public PlayerStatistic getPlayerStatistic() {
        return playerStatistic;
    }

    public void setPlayerStatistic(PlayerStatistic playerStatistic) {
        this.playerStatistic = playerStatistic;
    }
}
