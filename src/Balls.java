import java.awt.*;
import java.util.ArrayList;

public class Balls {

    private final ArrayList<Ball> balls;
    private int currentIndex = 0;

    public Balls() {
        balls = new ArrayList<>();
        addBall(new Ball(466,650,496,680,481,665, new Color(206, 18, 46).getRGB(),2, false, 1, 3));
    }

    public ArrayList<Ball> getGameBalls() {
        return balls;
    }

    public Ball getBall(int index){
        return balls.get(index);
    }

    public void addBall(Ball ball){
        balls.add(ball);
    }
    public void removeBall(Ball ball){

    }

    public void changeVisibility(Platform platform){

    }
}
