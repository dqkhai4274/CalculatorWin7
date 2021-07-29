package view;

public class StandardKeyboard implements Keyboard {
    public static final int NUM_BUTTONS = 28;
    @Override
    public MyButton[] buildButtons() {
        MyButton[] buttons = new MyButton[28];
        int tempX = Calculator.X_TOPLEFT;
        int tempY = Calculator.Y_TOPLEFT+ Calculator.HEIGHT+ Calculator.H_SPACE*4;
        int bCount = 0;
        int kCount = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){
                if(standardKeys[kCount].equals("0")){
                    buttons[bCount] = new MyButton(tempX, tempY, 2* Calculator.WIDTH+3* Calculator.W_SPACE, Calculator.HEIGHT, standardKeys[kCount]);
                    bCount++;
                }else if(standardKeys[kCount].equals("=")){
                    buttons[bCount] = new MyButton(tempX, tempY, Calculator.WIDTH+ Calculator.W_SPACE, Calculator.HEIGHT*2+ Calculator.H_SPACE, standardKeys[kCount]);
                    bCount++;
                }else if(!standardKeys[kCount].equals(" ")){
                    buttons[bCount] = new MyButton(tempX, tempY, Calculator.WIDTH+ Calculator.W_SPACE, Calculator.HEIGHT, standardKeys[kCount]);
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
