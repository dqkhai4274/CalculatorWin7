package view.keyboard;

import view.Button.*;
import view.Calculator;

import javax.swing.*;

public class SciencificKeyboard implements Keyboard{
    @Override
    public MyButtonInterface[] buildButtons() {
        MyButtonInterface[] buttons = new MyButtonInterface[58];

        buttons[55] = new UnitButton("Degree");
        buttons[56] = new UnitButton("Radians");
        buttons[57] = new UnitButton("Grads");

        buttons[0] = new MemoryButton("MC");
        buttons[1] = new MemoryButton("MR");
        buttons[2] = new MemoryButton("MS");
        buttons[3] = new MemoryButton("M+");
        buttons[4] = new MemoryButton("M-");
        buttons[5] = new NotImplementButton(" ");
        buttons[6] = new NotImplementButton("lnv");
        buttons[7] = new MonoOperationButton("ln");
        buttons[8] = new ParenthesesButton("(");
        buttons[9] = new ParenthesesButton(")");
        buttons[10] = new UndoButton("<--");
        buttons[11] = new ClearButton("CE");
        buttons[12] = new ClearButton("C");
        buttons[13] = new MonoOperationButton("NE");
        buttons[14] = new MonoOperationButton("sqrt");
        buttons[15] = new MonoOperationButton("Int");
        buttons[16] = new MonoOperationButton("sinh");
        buttons[17] = new MonoOperationButton("sin");
        buttons[18] = new MonoOperationButton("x^2");
        buttons[19] = new MonoOperationButton("n!");
        buttons[20] = new DigitButton("7");
        buttons[21] = new DigitButton("8");
        buttons[22] = new DigitButton("9");
        buttons[23] = new OperationButton("/");
        buttons[24] = new MonoOperationButton("%");
        buttons[25] = new MonoOperationButton("dms");
        buttons[26] = new MonoOperationButton("cosh");
        buttons[27] = new MonoOperationButton("cos");
        buttons[28] = new OperationButton("x^y");
        buttons[29] = new OperationButton("y√x");
        buttons[30] = new DigitButton("4");
        buttons[31] = new DigitButton("5");
        buttons[32] = new DigitButton("6");
        buttons[33] = new OperationButton("*");
        buttons[34] = new MonoOperationButton("1/x");
        buttons[35] = new NotImplementButton("pi");
        buttons[36] = new MonoOperationButton("tanh");
        buttons[37] = new MonoOperationButton("tan");
        buttons[38] = new MonoOperationButton("x^3");
        buttons[39] = new MonoOperationButton("3√x");
        buttons[40] = new DigitButton("1");
        buttons[41] = new DigitButton("2");
        buttons[42] = new DigitButton("3");
        buttons[43] = new OperationButton("-");
        buttons[44] = new ResultButton("=");
        buttons[45] = new NotImplementButton("F-E");
        buttons[46] = new OperationButton("exp");
        buttons[47] = new OperationButton("mod");
        buttons[48] = new MonoOperationButton("log");
        buttons[49] = new MonoOperationButton("10^x");
        buttons[50] = new DigitButton("0");
        buttons[51] = new DigitButton("empty");
        buttons[52] = new DotButton(".");
        buttons[53] = new OperationButton("+");
        buttons[54] = new DigitButton("empty");

        //set Position for each button
        int tempX = Calculator.X_TOPLEFT;
        int tempY = Calculator.Y_TOPLEFT+ Keyboard.HEIGHT_BUTTON + Calculator.H_SPACE*4;
        int bCount = 0;
        //set position for choice button
        for(int i = 55; i < 58; i++){
            int tmp = (int)(Keyboard.WIDTH_BUTTON * 2.5);
            ((AbstractButton)buttons[i]).setBounds(tempX, tempY, tmp, Keyboard.HEIGHT_BUTTON);
            tempX += tmp;
        }
        tempX = Calculator.X_TOPLEFT + (Keyboard.WIDTH_BUTTON+Calculator.W_SPACE*2)*5;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 10; j++){
                AbstractButton tmp = ((AbstractButton)buttons[bCount]);
                if(i == 0 && j < 5)
                    continue;
                if(tmp.getText().equals("0")){
                    tmp.setBounds(tempX, tempY, 2* Keyboard.WIDTH_BUTTON +3* Calculator.W_SPACE, Keyboard.HEIGHT_BUTTON);
                }else if(tmp.getText().equals("=")){
                    tmp.setBounds(tempX, tempY, Keyboard.WIDTH_BUTTON + Calculator.W_SPACE, Keyboard.HEIGHT_BUTTON *2+ Calculator.H_SPACE);
                }else if(!tmp.getText().equals("empty")){
                    tmp.setBounds(tempX, tempY, Keyboard.WIDTH_BUTTON + Calculator.W_SPACE, Keyboard.HEIGHT_BUTTON);
                }
                bCount++;
                tempX += Keyboard.WIDTH_BUTTON + Calculator.W_SPACE*2;
            }
            tempY += Keyboard.HEIGHT_BUTTON + Calculator.H_SPACE;
            tempX = Calculator.X_TOPLEFT;
        }

        return buttons;
    }
}
