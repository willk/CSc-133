package a3;

/**
 * Created by William Kinderman on 3/14/15.
 */
public interface IObservable {
    public void addObserver(IObserver o);

    public void notifyObservers();
}
