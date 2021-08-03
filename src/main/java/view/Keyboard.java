package view;

import view.Button.MyButton;

public interface Keyboard {
    String[] standardKeys = {"MC", "MR", "MS", "M+", "M-",
                            "<--", "CE", "C", "NE", "sqrt",
                            "7", "8", "9", "/", "%",
                            "4", "5", "6", "*", "1/x",
                            "1", "2", "3", "-", "=",
                            "0", " ", ".", "+", " "};
    MyButton[] buildButtons();
}
