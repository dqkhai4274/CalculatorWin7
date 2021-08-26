package view.mode;

import model.scienceMode.ScientificExpression;
import view.Calculator;
import view.display.ResultDisplay;
import view.keyboard.Keyboard;
import view.keyboard.scientificKeyboard;


public class ScientificMode extends Calculator {

    @Override
    public void setup() {
        setWindowSize(FRAME_WIDTH*2 - 2 * W_SPACE * 3, FRAME_HEIGHT);
        setDisplay(new ResultDisplay(Calculator.X_TOPLEFT, Calculator.Y_TOPLEFT+ Calculator.H_SPACE*2, 500, Keyboard.HEIGHT_BUTTON+ W_SPACE));
        setKeyboard(new scientificKeyboard());
        setModel(new ScientificExpression());
    }

}
