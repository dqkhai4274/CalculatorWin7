package view.Button;

import model.ModelExpression;
import view.keyboard.KeyBoardObserver;
import view.keyboard.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class ChoiceButton extends JRadioButton implements MyButtonInterface {
    private ModelExpression model;
    private ArrayList<KeyBoardObserver> observers = new ArrayList<>();

    public ChoiceButton(String character){
        super(character);
        this.setMargin(new Insets(1,1,1,1));
        this.setSize(Keyboard.WIDTH_BUTTON, Keyboard.HEIGHT_BUTTON);
        addActionListener(this);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ChoiceButton tmp = (ChoiceButton) e.getSource();
        notifyObservers(this, tmp.getText());
    }

    @Override
    public void register(KeyBoardObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(Clickable button, String message) {
        for(KeyBoardObserver o : observers){
            o.update(button, message);
        }
    }

    public ModelExpression getMyModel(){
        return this.model;
    }

    public void setModel(ModelExpression model){
        this.model = model;
    }
}
