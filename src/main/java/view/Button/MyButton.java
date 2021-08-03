package view.Button;

import model.ModelExpression;
import model.StandardExpression;
import view.KeyBoardObserver;
import view.KeyboardPublisher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class MyButton extends JButton implements ActionListener, KeyboardPublisher {
    private ModelExpression model;
    private ArrayList<KeyBoardObserver> observers = new ArrayList<>();

    public MyButton(String character){
        super(character);
        this.setMargin(new Insets(1,1,1,1));
        addActionListener(this);
    }

    public abstract void click(String message);

    public ModelExpression getMyModel(){
        return this.model;
    }

    public void setModel(ModelExpression model){
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyButton tmp = (MyButton) e.getSource();
        notifyObservers(this, tmp.getText());
    }

    @Override
    public void register(KeyBoardObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(MyButton button, String message) {
        for(KeyBoardObserver o : observers){
            o.update(button, message);
        }
    }

}
