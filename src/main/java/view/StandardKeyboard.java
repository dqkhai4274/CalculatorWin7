package view;

import view.Button.*;

public class StandardKeyboard implements Keyboard {
    public static final int NUM_BUTTONS = 28;
    public static final int WIDTH_BUTTON = Calculator.WIDTH+ Calculator.W_SPACE;
    public static final int HEIGHT_BUTTON = Calculator.HEIGHT;
    @Override
    public MyButton[] buildButtons() {
        MyButton[] buttons = new MyButton[28];
        // create button from left->right, top->down
        buttons[0] = new MemoryButton("MC");
        buttons[1] = new MemoryButton("MR");
        buttons[2] = new MemoryButton("MS");
        buttons[3] = new MemoryButton("M+");
        buttons[4] = new MemoryButton("M-");
        buttons[5] = new UndoButton("MR");
        buttons[6] = new ClearButton("CE");
        buttons[7] = new ClearButton("C");
        buttons[8] = new ExtendedOperationButton("NE");
        buttons[9] = new ExtendedOperationButton("sqrt");
        buttons[10] = new DigitButton("7");
        buttons[11] = new DigitButton("8");
        buttons[12] = new DigitButton("9");
        buttons[13] = new OperationButton("/");
        buttons[14] = new ExtendedOperationButton("%");
        buttons[15] = new DigitButton("4");
        buttons[16] = new DigitButton("5");
        buttons[17] = new DigitButton("6");
        buttons[18] = new OperationButton("*");
        buttons[19] = new ExtendedOperationButton("1/x");
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
        int tempY = Calculator.Y_TOPLEFT+ Calculator.HEIGHT+ Calculator.H_SPACE*4;
        int bCount = 0;
        int kCount = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){
                if(standardKeys[kCount].equals("0")){
                    buttons[bCount].setBounds(tempX, tempY, 2* Calculator.WIDTH+3* Calculator.W_SPACE, Calculator.HEIGHT);
                    bCount++;
                }else if(standardKeys[kCount].equals("=")){
                    buttons[bCount].setBounds(tempX, tempY, Calculator.WIDTH+ Calculator.W_SPACE, Calculator.HEIGHT*2+ Calculator.H_SPACE);
                    bCount++;
                }else if(!standardKeys[kCount].equals(" ")){
                    buttons[bCount].setBounds(tempX, tempY, Calculator.WIDTH+ Calculator.W_SPACE, Calculator.HEIGHT);
                    bCount++;
                }
                kCount++;
                tempX += Calculator.WIDTH+ Calculator.W_SPACE*2;

            }
            tempY += Calculator.HEIGHT+ Calculator.H_SPACE;
            tempX = Calculator.X_TOPLEFT;
        }
        return buttons;
    }
}
