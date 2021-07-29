package view;

import java.awt.*;

public class StandardDisplay implements ResultDisplay {
    private Label oldLabel = new Label(" ", Label.RIGHT);
    private Label entryLabel = new Label("0", Label.RIGHT);
    @Override
    public Label getOldDisplay() {
        Label temp = oldLabel;
        temp.setBounds(Calculator.X_TOPLEFT, Calculator.Y_TOPLEFT,
                240, Calculator.HEIGHT);
        temp.setBackground(ResultDisplay.DISPLAY_COLOR);

        return temp;
    }

    public Label getDisplayEntry(){
        entryLabel.setBounds(Calculator.X_TOPLEFT, Calculator.Y_TOPLEFT+ Calculator.H_SPACE*2,
                240, Calculator.HEIGHT);
        entryLabel.setBackground(DISPLAY_COLOR);
        return entryLabel;
    }
    //in standard mode, we also have to update both display(old and current display)
    @Override
    public void update(String messageForEntry, String messageForOld) {
        if(!messageForOld.equals("")){
            this.oldLabel.setText(messageForOld);
        }
        this.entryLabel.setText(messageForEntry);
    }

    private static boolean containNonDigit(String message){
        for(Character c : message.toCharArray()){
            if(!(c.equals('.') || Character.isDigit(c))){
                return true;
            }
        }
        return false;
    }
}
