package view.Button;

import view.Button.MyButton;

public class MemoryButton extends MyButton {

    public MemoryButton(String character) {
        super(character);
    }

    @Override
    public void click(String message) {
        getMyModel().clickMemory(message);
    }
}
