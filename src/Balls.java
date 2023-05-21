import java.awt.*;
import java.util.ArrayList;

public class Balls {

    private final ArrayList<Ball> balls;
    private int currentIndex = 0;

    public Balls(double koef, int width, int height) {
        balls = new ArrayList<>();
        addBall(new Ball((int) (516 * koef), (int) (630 * koef), (int) (546 * koef), (int) (660 * koef), (int) (531 * koef), (int) (645 * koef), new Color(206, 18, 46).getRGB(), 2, false, 1, 3, width, height));
    }

    public ArrayList<Ball> getGameBalls() {
        return balls;
    }

    public Ball getBall(int index) {
        return balls.get(index);
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public void removeBall(Ball ball) {

    }

    public void changeVisibility(Platform platform) {

    }
}
