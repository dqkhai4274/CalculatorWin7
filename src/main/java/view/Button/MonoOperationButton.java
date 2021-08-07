package view.Button;

public class MonoOperationButton extends MyButton {

    public MonoOperationButton(String character) {
        super(character);
    }

    @Override
    public void click(String message) {
        getMyModel().clickMonoOperation(message);
    }
}
