package view.keyboard;

import view.Button.*;
import view.Calculator;

import javax.swing.*;

public class StandardKeyboard implements Keyboard {
    public static final int NUM_BUTTONS = 28;

    @Override
    public MyButtonInterface[] buildButtons() {
        MyButtonInterface[] buttons = new MyButtonInterface[NUM_BUTTONS];
        // create button from left->right, top->down
        buttons[0] = new MemoryButton("MC");
        buttons[1] = new MemoryButton("MR");
        buttons[2] = new MemoryButton("MS");
        buttons[3] = new MemoryButton("M+");
        buttons[4] = new MemoryButton("M-");
        buttons[5] = new UndoButton("<--");
        buttons[6] = new ClearButton("CE");
        buttons[7] = new ClearButton("C");
        buttons[8] = new MonoOperationButton("NE");
        buttons[9] = new MonoOperationButton("sqrt");
        buttons[10] = new DigitButton("7");
        buttons[11] = new DigitButton("8");
        buttons[12] = new DigitButton("9");
        buttons[13] = new OperationButton("/");
        buttons[14] = new MonoOperationButton("%");
        buttons[15] = new DigitButton("4");
        buttons[16] = new DigitButton("5");
        buttons[17] = new DigitButton("6");
        buttons[18] = new OperationButton("*");
        buttons[19] = new MonoOperationButton("1/x");
        buttons[20] = new DigitButton("1");
        buttons[21] = new DigitButton("2");
        buttons[22] = new DigitButton("3");
        buttons[23] = new OperationButton("-");
        buttons[24] = new ResultButton("=");
        buttons[25] = new DigitButton("0");
        buttons[26] = new DotButton(".");
        buttons[27] = new OperationButton("+");

        //set Position for each button
        int tempX = Calculator.X_TOPLEFT;
        int tempY = Calculator.Y_TOPLEFT+ Keyboard.HEIGHT_BUTTON + Calculator.H_SPACE*4;
        int bCount = 0;
        int kCount = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){

                if(standardKeys[kCount].equals("0")){
                    ((AbstractButton)buttons[bCount]).setBounds(tempX, tempY, 2* Keyboard.WIDTH_BUTTON +3* Calculator.W_SPACE, Keyboard.HEIGHT_BUTTON);
                    bCount++;
                }else if(standardKeys[kCount].equals("=")){
                    ((AbstractButton)buttons[bCount]).setBounds(tempX, tempY, Keyboard.WIDTH_BUTTON + Calculator.W_SPACE, Keyboard.HEIGHT_BUTTON *2+ Calculator.H_SPACE);
                    bCount++;
                }else if(!standardKeys[kCount].equals(" ")){
                    ((AbstractButton)buttons[bCount]).setBounds(tempX, tempY, Keyboard.WIDTH_BUTTON + Calculator.W_SPACE, Keyboard.HEIGHT_BUTTON);
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
