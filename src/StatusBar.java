import java.io.Serializable;

public class StatusBar implements Serializable {
    private String name;
    private String surname;
    private String progressBar;
    private String time;
    private String destroyed;
    transient private Menu menu;

    public StatusBar() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(String progressBar) {
        this.progressBar = progressBar;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getDestroyed() {
        return destroyed;
    }

    public void setDestroyed(String destroyed) {
        this.destroyed = destroyed;
    }
}