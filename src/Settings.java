import java.io.Serializable;
import java.lang.reflect.Field;

public class Settings implements Serializable {
    int difficulty;

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    int screen;



    transient SettingsFrame settingsFrame;

    Settings() {
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }


    public SettingsFrame getSettingsFrame() {
        return settingsFrame;
    }

    public void setSettingsFrame(SettingsFrame settingsFrame) {
        this.settingsFrame = settingsFrame;
    }
}