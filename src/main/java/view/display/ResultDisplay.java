package view.display;

import model.*;
import view.Calculator;
import view.keyboard.Keyboard;

import javax.swing.*;
import java.awt.*;


public class ResultDisplay implements ModelObserver {
    private JLabel oldLabel = new JLabel(" ", SwingConstants.RIGHT);
    private JLabel entryLabel = new JLabel("0", SwingConstants.RIGHT);

    public static final Color DISPLAY_COLOR = Color.CYAN;

    public ResultDisplay(int topleft_x, int topleft_y, int width, int height){
        oldLabel.setBackground(ResultDisplay.DISPLAY_COLOR);
        oldLabel.setForeground(Color.BLACK);
        oldLabel.setBounds(topleft_x, topleft_y-height, width, height);

        entryLabel.setForeground(Color.BLACK);
        entryLabel.setBackground(DISPLAY_COLOR);
        entryLabel.setBounds(topleft_x, topleft_y, width, height);
    }

    public JLabel getOldDisplay() {
        return oldLabel;
    }

    public JLabel getDisplayEntry(){
        return entryLabel;
    }

    @Override
    public void update(String messageForEntry, String messageForOld) {
        if(!messageForOld.equals("")){
            this.oldLabel.setText(messageForOld);
        }
        this.entryLabel.setText(messageForEntry);
    }
}
