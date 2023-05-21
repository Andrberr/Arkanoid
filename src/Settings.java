import java.io.Serializable;
import java.lang.reflect.Field;

public class Settings implements Serializable {
    int volume;
    int brightness;
    int difficulty;

    String screen;

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    transient SettingsFrame settingsFrame;

    Settings() {
    }


    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public SettingsFrame getSettingsFrame() {
        return settingsFrame;
    }

    public void setSettingsFrame(SettingsFrame settingsFrame) {
        this.settingsFrame = settingsFrame;
    }
}