package view.keyboard;

import view.Button.Clickable;

public interface KeyBoardObserver {
    void update(Clickable button, String message);
}
