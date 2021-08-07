package view.Button;

public class ParenthesesButton extends MyButton{
    public ParenthesesButton(String character) {
        super(character);
    }

    @Override
    public void click(String message) {
       getMyModel().clickParentheses(message);
    }
}
