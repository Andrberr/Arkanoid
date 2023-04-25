import java.awt.*;

public class Balls {
    Ball[] balls;

    public Balls(){
        balls = new Ball[1];
        addBall();
    }

    public Ball[] getBalls() {
        return balls;
    }

    void addBall(){
        Ball ball = new Ball(1170 / 2 - 20, 580, 1170 / 2 + 20, 620, 1170 / 2, 600, 3, 10, new Color(206, 18, 46).getRGB(), true);
        this.balls[0] = ball;
    }
}
