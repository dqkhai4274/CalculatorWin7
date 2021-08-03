package view.Button;

public class DotButton extends MyButton {

    public DotButton(String character) {
        super(character);
    }

    @Override
    public void click(String message) {
        getMyModel().clickDot();
    }
}
