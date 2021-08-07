package view.Button;

public class ClearButton extends MyButton {

    public ClearButton(String character) {
        super(character);
    }

    @Override
    public void click(String message) {
        getMyModel().clickClear(message);
    }
}
