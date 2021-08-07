package view.menu;

import view.Calculator;
import view.mode.ProgrammerMode;

import java.awt.event.ActionEvent;

public class ProgrammerModeMenuItem extends ModeMenuItem{
    public ProgrammerModeMenuItem(String name, Calculator c) {
        super(name, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getCalculator().dispose();
        new ProgrammerMode();
    }
}
