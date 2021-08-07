package view.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MyMenuItem extends JMenuItem implements ActionListener {

    public MyMenuItem(String name){
        super(name);
        addActionListener(this);
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
