import java.util.Observable;

public class EventSource extends Observable {
    public void fireEvent(Event event) {
        setChanged();
        notifyObservers(event);
    }
}
