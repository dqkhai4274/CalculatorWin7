package view.menu;


import view.Calculator;
import view.mode.StandardMode;

import java.awt.event.ActionEvent;

public class StandardModeMenuItem extends ModeMenuItem{

    public StandardModeMenuItem(String name, Calculator c) {
        super(name, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getCalculator().dispose();
        new StandardMode();
    }
}
