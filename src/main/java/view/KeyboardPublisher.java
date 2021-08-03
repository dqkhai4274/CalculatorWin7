package view;

import view.Button.MyButton;

public interface KeyboardPublisher {
    void register(KeyBoardObserver observer);
    void notifyObservers(MyButton button, String message);
}
