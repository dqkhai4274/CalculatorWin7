package view.menu;

import view.Calculator;
import view.mode.ScientificMode;

import java.awt.event.ActionEvent;

public class ScientificModeMenuItem extends ModeMenuItem {

    public ScientificModeMenuItem(String name, Calculator c) {
        super(name, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getCalculator().dispose();
        new ScientificMode();
    }
}
