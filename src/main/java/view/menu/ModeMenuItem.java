package view.menu;

import view.Calculator;

import java.awt.event.ActionEvent;

public abstract class ModeMenuItem extends MyMenuItem {
    private Calculator c;
    public ModeMenuItem(String name, Calculator c) {
        super(name);
        this.c = c;
    }

    public Calculator getCalculator(){
        return this.c;
    }

    public abstract void actionPerformed(ActionEvent e);
}
