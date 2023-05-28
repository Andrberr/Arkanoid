import java.awt.*;
import java.util.ArrayList;

public class Balls {

    private final ArrayList<Ball> balls;
    private int currentIndex = 0;

    public Balls(double koef, int width, int height) {
        balls = new ArrayList<>();
        double k = koef * 30;
        int startY = height - 30 - (int) k;
        int centerY = startY + (height - 30 - startY) / 2;
        Ball ball = new Ball((int) (516 * koef), startY, (int) (546 * koef), height - 30, (int) (531 * koef), centerY, new Color(206, 18, 46).getRGB(), 2, false, 1, 3, width, height);
        addBall(ball);
        DisplayObjects.eventSource.addObserver(ball);
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
