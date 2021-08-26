package view.keyboard;

import view.Calculator;
import view.MyButton;

public class StandardKeyboard implements Keyboard {
    public static final int NUM_BUTTONS = 29;
    public static final String[] STANDARD_KEYS = {"MC", "MR", "MS", "M+", "M-",
                                                "<--", "CE", "C", "NE", "sqrt",
                                                "7", "8", "9", "/", "%",
                                                "4", "5", "6", "*", "1/x",
                                                "1", "2", "3", "-", "=",
                                                "0", " ", ".", "+", " "};
    @Override
    public MyButton[] buildButtons() {


        MyButton[] buttons = new MyButton[NUM_BUTTONS];

        //set Position for each button
        int tempX = Calculator.X_TOPLEFT;
        int tempY = Calculator.Y_TOPLEFT+ Keyboard.HEIGHT_BUTTON + Calculator.H_SPACE*4;
        int bCount = 0;
        int kCount = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){
                buttons[bCount] = new MyButton(STANDARD_KEYS[kCount]);
                MyButton tmp = buttons[bCount];
                if(tmp.getText().equals("0")){
                    tmp.setBounds(tempX, tempY, 2* Keyboard.WIDTH_BUTTON +3* Calculator.W_SPACE, Keyboard.HEIGHT_BUTTON);
                    bCount++;
                }else if(tmp.getText().equals("=")){
                    tmp.setBounds(tempX, tempY, Keyboard.WIDTH_BUTTON + Calculator.W_SPACE, Keyboard.HEIGHT_BUTTON *2+ Calculator.H_SPACE);
                    bCount++;
                }else if(!tmp.getText().equals(" ")){
                    tmp.setBounds(tempX, tempY, Keyboard.WIDTH_BUTTON + Calculator.W_SPACE, Keyboard.HEIGHT_BUTTON);
                    bCount++;
                }
                kCount++;
                tempX += Keyboard.WIDTH_BUTTON + Calculator.W_SPACE*2;

            }
            tempY += Keyboard.HEIGHT_BUTTON + Calculator.H_SPACE;
            tempX = Calculator.X_TOPLEFT;
        }
        return buttons;
    }
}
