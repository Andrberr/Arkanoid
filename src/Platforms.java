import java.awt.*;
import java.util.ArrayList;

public class Platforms {

    private ArrayList<Platform> platforms;
    private int currentIndex = 0;

    public Platforms(double koef, int width, int height) {
        platforms = new ArrayList<>();
        Platform platform = new Platform((int) (450 * koef), (int) (660 * koef), (int) (610 * koef), (int) (675 * koef), (int) (530 * koef), (int) (667 * koef), new Color(36, 53, 164).getRGB(), 3, false, 5, 0, width, height);
        addDesk(platform);
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
