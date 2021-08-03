package view.Button;

public class DigitButton extends MyButton {

    public DigitButton(String character) {
        super(character);
    }

    @Override
    public void click(String message) {
        getMyModel().clickDigit(message);
    }
}
