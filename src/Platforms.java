import java.awt.*;
import java.util.ArrayList;

public class Platforms {

    private ArrayList<Platform> platforms;
    private int currentIndex = 0;

    public Platforms() {
        platforms = new ArrayList<>();
        Platform platform = new Platform(400, 680, 560, 695, 480, 687, new Color(36, 53, 164).getRGB(), 3, false, 5, 0);
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
