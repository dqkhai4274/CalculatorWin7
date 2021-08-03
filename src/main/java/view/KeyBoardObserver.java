package view;

import view.Button.MyButton;

public interface KeyBoardObserver {
    void update(MyButton button, String message);
}
