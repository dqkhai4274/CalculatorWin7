package view.Button;

public class OperationButton extends MyButton {

    public OperationButton(String character) {
        super(character);
    }

    @Override
    public void click(String message) {
        getMyModel().clickOperation(message);
    }
}
