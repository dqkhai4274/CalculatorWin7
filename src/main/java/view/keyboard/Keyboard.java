package view.keyboard;

import view.Button.MyButton;
import view.Button.MyButtonInterface;

import javax.swing.*;

public interface Keyboard {
    String[] standardKeys = {"MC", "MR", "MS", "M+", "M-",
                            "<--", "CE", "C", "NE", "sqrt",
                            "7", "8", "9", "/", "%",
                            "4", "5", "6", "*", "1/x",
                            "1", "2", "3", "-", "=",
                            "0", " ", ".", "+", " "};

    int HEIGHT_BUTTON = 30;
    int WIDTH_BUTTON = 30;

    MyButtonInterface[] buildButtons();
}
