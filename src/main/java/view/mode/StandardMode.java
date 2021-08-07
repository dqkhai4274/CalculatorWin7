package view.mode;

import model.standardMode.StandardExpression;
import view.Calculator;
import view.display.ResultDisplay;
import view.keyboard.Keyboard;
import view.keyboard.StandardKeyboard;


public class StandardMode extends Calculator {

    public void setup() {
        setDisplay(new ResultDisplay(Calculator.X_TOPLEFT, Calculator.Y_TOPLEFT, 240, Keyboard.HEIGHT_BUTTON));
        setKeyboard(new StandardKeyboard());
        setModel(new StandardExpression());
        setWindowSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

}
