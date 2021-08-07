package view.keyboard;

import view.Button.Clickable;
import view.Button.MyButton;
import view.keyboard.KeyBoardObserver;

public interface KeyboardPublisher {
    void register(KeyBoardObserver observer);
    void notifyObservers(Clickable button, String message);
}
