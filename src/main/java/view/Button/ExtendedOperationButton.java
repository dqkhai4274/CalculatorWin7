package view.Button;

public class ExtendedOperationButton extends MyButton {

    public ExtendedOperationButton(String character) {
        super(character);
    }

    @Override
    public void click(String message) {
        getMyModel().clickExtendedOperation(message);
    }
}
