import java.awt.*;
import java.util.ArrayList;

public class Platforms {

    private ArrayList<Platform> platforms;
    private int currentIndex = 0;

    public Platforms(double koef, int width, int height) {
        platforms = new ArrayList<>();
        double k = koef * 15;
        int endY = height - 30 + (int) k;
        int centerY = height - 30 + (endY - height - 30) / 2;
        Platform platform = new Platform((int) (450 * koef), height - 30, (int) (610 * koef), endY, (int) (530 * koef), centerY, new Color(36, 53, 164).getRGB(), 3, false, 5, 0, width, height);
        addDesk(platform);

        DisplayObjects.eventSource.addObserver(platform);
    }

    public ArrayList<Platform> getBallDesks() {
        return platforms;
    }

    public Platform getDesk(int index) {
        return (Platform) platforms.get(index);
    }

    public void addDesk(Platform platform) {
        platforms.add(platform);
    }

    public void removeDesk(Platform platform) {

    }

    public void changeVisibility(Platform platform) {

    }
}
