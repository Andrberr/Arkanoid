import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Settings implements Serializable {
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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }


    public void setSettingsFrame(SettingsFrame settingsFrame) {
        this.settingsFrame = settingsFrame;
    }
}