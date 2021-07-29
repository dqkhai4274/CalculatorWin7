package model;

public interface ModelObserver {
    void update(String messageForEntry, String messageForOld);
}
