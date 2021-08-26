package view.keyboard;

import view.MyButton;

public interface Keyboard {
    int HEIGHT_BUTTON = 30;
    int WIDTH_BUTTON = 30;

    MyButton[] buildButtons();
}
