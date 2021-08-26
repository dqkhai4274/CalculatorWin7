package view;

import view.keyboard.KeyBoardObserver;
import view.keyboard.KeyboardPublisher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyButton extends JButton implements ActionListener, KeyboardPublisher {
    private ArrayList<KeyBoardObserver> observers = new ArrayList<>();

    public MyButton(String character){
        super(character);
        this.setMargin(new Insets(1,1,1,1));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyButton tmp = (MyButton) e.getSource();
        notifyObservers(tmp.getText());
    }

    @Override
    public void register(KeyBoardObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for(KeyBoardObserver o : observers){
            o.update(message);
        }
    }

}
