package a4;

/**
 * Created by William Kinderman on 3/14/15.
 */
public interface IObservable {
    void addObserver(IObserver o);

    void notifyObservers();
}
