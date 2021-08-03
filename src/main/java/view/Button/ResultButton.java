package view.Button;

import view.Button.MyButton;

public class ResultButton extends MyButton {

    public ResultButton(String character) {
        super(character);
    }

    @Override
    public void click(String message) {
        getMyModel().clickEqual();
    }
}
