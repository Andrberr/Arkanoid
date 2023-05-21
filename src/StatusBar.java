import java.io.Serializable;

public class StatusBar implements Serializable {

    int percent;
    String name;
    String surname;
    int score;
    String time;

    public StatusBar(int percent, String name, String surname, int score, String time) {
        this.percent = percent;
        this.name = name;
        this.surname = surname;
        this.score = score;
        this.time = time;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}