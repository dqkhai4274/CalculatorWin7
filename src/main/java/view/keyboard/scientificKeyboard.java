package view.keyboard;

import view.Calculator;
import view.MyButton;

public class scientificKeyboard implements Keyboard{
    public static final String[] SCIENTIFIC_KEYBOARD = {"MC", "MR", "MS", "M+", "M-",
            " ", "lnv", "ln", "(", ")", "<--", "CE", "C", "NE", "sqrt",
            "Int", "sinh", "sin", "x^2", "n!", "7", "8", "9", "/", "%",
            "dms", "cosh", "cos", "x^y", "y√x", "4", "5", "6", "*", "1/x",
            "π", "tanh", "tan", "x^3", "3√x", "1", "2", "3", "-", "=",
            "F-E", "exp", "mod", "log", "10^x", "0", "empty", ".", "+", "empty", "Degrees", "Radians", "Grads"};
    @Override
    public MyButton[] buildButtons() {
        MyButton[] buttons = new MyButton[56];
        //set Position for each button
        int tempY = Calculator.Y_TOPLEFT+ Keyboard.HEIGHT_BUTTON + Calculator.H_SPACE*4;
        int bCount = 0;
        int kCount = 0;
        //set position for choice button

        int tempX = Calculator.X_TOPLEFT + (Keyboard.WIDTH_BUTTON+Calculator.W_SPACE*2)*5;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 10; j++){
                if(i * 10 + j < 5)
                    continue;
                buttons[bCount] = new MyButton(SCIENTIFIC_KEYBOARD[kCount]);
                MyButton tmp = buttons[bCount];
                if(tmp.getText().equals("0")){
                    tmp.setBounds(tempX, tempY, 2* Keyboard.WIDTH_BUTTON +3* Calculator.W_SPACE, Keyboard.HEIGHT_BUTTON);
                    bCount++;
                }else if(tmp.getText().equals("=")){
                    tmp.setBounds(tempX, tempY, Keyboard.WIDTH_BUTTON + Calculator.W_SPACE, Keyboard.HEIGHT_BUTTON *2+ Calculator.H_SPACE);
                    bCount++;
                }else if(!tmp.getText().equals("empty")){
                    tmp.setBounds(tempX, tempY, Keyboard.WIDTH_BUTTON + Calculator.W_SPACE, Keyboard.HEIGHT_BUTTON);
                    bCount++;
                }
                kCount++;
                tempX += Keyboard.WIDTH_BUTTON + Calculator.W_SPACE*2;
            }
            tempY += Keyboard.HEIGHT_BUTTON + Calculator.H_SPACE;
            tempX = Calculator.X_TOPLEFT;
        }

        tempX = Calculator.X_TOPLEFT;
        tempY = Calculator.Y_TOPLEFT+ Keyboard.HEIGHT_BUTTON + Calculator.H_SPACE*4;
        for(int i = 53; i < 56; i++){
            int tmp = (int)(Keyboard.WIDTH_BUTTON * 2.5);
            System.out.println(SCIENTIFIC_KEYBOARD[i+2]);
            buttons[i] = new MyButton(SCIENTIFIC_KEYBOARD[i+2]);
            buttons[i].setBounds(tempX, tempY, tmp, Keyboard.HEIGHT_BUTTON);
            tempX += tmp;
        }
        return buttons;
    }
}
