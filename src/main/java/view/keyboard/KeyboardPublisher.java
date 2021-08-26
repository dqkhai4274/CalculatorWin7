package view.keyboard;

public interface KeyboardPublisher {
    void register(KeyBoardObserver observer);
    void notifyObservers(String message);
}
