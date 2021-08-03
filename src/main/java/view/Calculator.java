package view;

import model.ModelExpression;
import model.StandardExpression;
import view.Button.MyButton;

import javax.swing.*;

public class Calculator{
    public static final int FRAME_WIDTH = 325;
    public static final int FRAME_HEIGHT = 400;
    public static final int W_SPACE = 10;
    public static final int H_SPACE = 10;
    public static final int X_TOPLEFT = 30;
    public static final int Y_TOPLEFT = 20;
    public static final int HEIGHT = 30;
    public static final int WIDTH = 30;

    private JFrame calculator;
    private Keyboard keyboard;
    private JMenuBar menuBar = new JMenuBar();
    private ResultDisplay display;
    private ModelExpression model;

    // default mode is standard mode
    public Calculator(){
        calculator = new JFrame("view.Calculator");
        calculator.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        initMenuBar();
        display = new StandardDisplay();
        keyboard = new StandardKeyboard();
        // for handle logic and data
        model = new StandardExpression();

        calculator.setJMenuBar(menuBar);
        calculator.add(display.getOldDisplay());
        calculator.add(display.getDisplayEntry());
        for(MyButton button : keyboard.buildButtons()) {
            calculator.add(button);
            // every change in button will move to model
            button.register(model);
            button.setModel(model);
        }
        //display will observe the change in model, then update to screen
        model.register(display);

        calculator.setLayout(null);
        calculator.setVisible(true);
    }

    void initMenuBar(){
        JMenu viewMenu = new JMenu("View");
        viewMenu.add(new MyMenuItem( "Standard"));
        viewMenu.add(new MyMenuItem( "Scientific"));
        viewMenu.add(new MyMenuItem("Programmer"));
        viewMenu.add(new MyMenuItem( "Statistics"));
        viewMenu.add(new MyMenuItem( "History"));
        viewMenu.add(new MyMenuItem("Digit Group"));

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(new MyMenuItem( "Copy"));
        editMenu.add(new MyMenuItem( "Paste"));
        editMenu.add(new MyMenuItem("History"));

        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(new MyMenuItem( "View Help"));
        helpMenu.add(new MyMenuItem( "About view.Calculator"));

        menuBar.add(viewMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
    }
}
