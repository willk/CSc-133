package a2;

/**
 * Created by willk on 3/14/15.
 */
public interface IObservable {
    public void addObserver(IObserver o);

    public void notifyObserver();
}
