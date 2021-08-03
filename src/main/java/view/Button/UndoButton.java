package view.Button;

import view.Button.MyButton;

public class UndoButton extends MyButton {

    public UndoButton(String character) {
        super(character);
    }

    @Override
    public void click(String message) {
        getMyModel().clickUndo();
    }
}
