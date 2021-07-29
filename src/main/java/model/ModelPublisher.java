package model;

public interface ModelPublisher {
    void register(ModelObserver observer);
    void notifyObservers(String messageForEntry, String messageForOld);
}
