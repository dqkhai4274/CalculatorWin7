package view;

import model.ModelExpression;
import view.Button.MyButtonInterface;
import view.display.ResultDisplay;
import view.keyboard.Keyboard;
import view.menu.ProgrammerModeMenuItem;
import view.menu.ScientificModeMenuItem;
import view.menu.StandardModeMenuItem;


import javax.swing.*;

public abstract class Calculator extends JFrame{
    public static final int FRAME_WIDTH = 325;
    public static final int FRAME_HEIGHT = 400;
    public static final int W_SPACE = 10;
    public static final int H_SPACE = 10;
    public static final int X_TOPLEFT = 30;
    public static final int Y_TOPLEFT = 20;

    private Keyboard keyboard;
    private ResultDisplay display;
    private ModelExpression model;

    public Calculator(){
        super("Calculator");
        this.setJMenuBar(initMenuBar());
        // default mode is standard mode
        setup();

        this.add(display.getOldDisplay());
        this.add(display.getDisplayEntry());

        for(MyButtonInterface button : keyboard.buildButtons()) {
            this.add((AbstractButton) button);
            // every change in button will move to model
            button.register(model);
            button.setModel(model);
        }
        //display will observe the change in model, then update to screen
        model.register(display);

        this.setLayout(null);
        this.setVisible(true);
    }

    public abstract void setup();

    public JMenuBar initMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu viewMenu = new JMenu("View");
        viewMenu.add(new StandardModeMenuItem("Standard", this));
        viewMenu.add(new ScientificModeMenuItem( "Scientific", this));
        viewMenu.add(new ProgrammerModeMenuItem("Programmer", this));
//        viewMenu.add(new MyMenuItem( "Statistics"));
//        viewMenu.add(new MyMenuItem( "History"));
//        viewMenu.add(new MyMenuItem("Digit Group"));
//
//        JMenu editMenu = new JMenu("Edit");
//        editMenu.add(new MyMenuItem( "Copy"));
//        editMenu.add(new MyMenuItem( "Paste"));
//        editMenu.add(new MyMenuItem("History"));
//
//        JMenu helpMenu = new JMenu("Help");
//        helpMenu.add(new MyMenuItem( "View Help"));
//        helpMenu.add(new MyMenuItem( "About view.Calculator"));

        menuBar.add(viewMenu);
//        menuBar.add(editMenu);
//        menuBar.add(helpMenu);
        return menuBar;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void setDisplay(ResultDisplay display){
        this.display = display;
    }

    public void setModel(ModelExpression model){
        this.model = model;
    }

    public Keyboard getKeyboard(){
        return keyboard;
    }

    public ResultDisplay getDisplay(){
        return this.display;
    }

    public ModelExpression getModel(){
        return this.model;
    }

    public void setWindowSize(int width, int height){
        this.setSize(width, height);
    }
}
