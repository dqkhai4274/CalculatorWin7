package model;

import view.Button.Clickable;
import view.keyboard.KeyBoardObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class ModelExpression implements KeyBoardObserver, ModelPublisher {
    private double memory;
    protected State state;
    private String lastMessage="0";
    private List<ModelObserver> observers = new ArrayList<>();

    private String operation = "";
    private double result;
    private double operand;
    private double operand1;

    public abstract void clickDigit(String message);
    public abstract void clickOperation(String message);
    public abstract void clickMonoOperation(String message);
    public abstract void clickEqual();
    public abstract void clickDot();
    public abstract void clickUndo();

    public void clickClear(String message){
        if(message.equals("C")){
            setOperand(0);
            setOperand1(0);
            setResult(0);
            setOperation("");
            notifyObservers("0", " ");
        }else {
            setOperand(0);
            notifyObservers("0", "");
        }
    }

    public void clickParentheses(String message){
        throw new UnsupportedOperationException();
    }

    public void clickMemory(String message){
        try{
            double temp = Double.parseDouble(lastMessage);
            switch (message){
                case "MC":
                    memory = 0;
                    break;
                case "MR":
                    state.clickMemoryRecall();
                    break;
                case "MS":
                    memory = temp;
                    break;
                case "M+":
                    memory += temp;
                    break;
                case "M-":
                    memory -= temp;
                    break;
            }
        }catch(NumberFormatException e){
            System.err.println("cant store this message");
        }
    }

    @Override
    public void update(Clickable button, String message) {
        button.click(OperationToWord(message));
    }

    @Override
    public void register(ModelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String messageForEntry, String messageForOld) {
        lastMessage = messageForEntry;
        for(ModelObserver observer: observers){
            observer.update(messageForEntry, messageForOld);
        }
    }

    public double getMemory(){
        return this.memory;
    }

    public void changeState(State state){
        this.state = state;
    }

    public void setOperand(double operand){
        this.operand = operand;
    }

    public double getOperand(){
        return this.operand;
    }

    public void setOperation(String operation){
        this.operation = operation;
    }

    public String getOperation(){
        return this.operation;
    }

    public void setResult(double result){
        this.result = result;
    }

    public double getResult() {
        return this.result;
    }

    public double getOperand1(){
        return this.operand1;
    }

    public void setOperand1(double operand1){
        this.operand1 = operand1;
    }

    public static String simplify(double d){
        // if result is a integer, simplify it
        if(d - (int)d == 0){
            return String.valueOf((int)d);
        }
        double base = 1;
        // try round to 1,2,3... digits after dot
        while(Math.abs(Math.round(d*base)/base - d) > Math.pow(10, -9)){
            base *= 10;
        }
        return String.valueOf(Math.round(d*base)/base);
    }

    public static String OperationToWord(String operation){
        switch (operation){
            case "n!" :
                return "fact";
            case "x^2":
                return "sqr";
            case "x^y":
                return "^";
            case "y√x":
                return "yroot";
            case "x^3":
                return "cube";
            case "3√x":
                return "cuberoot";
            case "√":
                return "sqrt";
            case "10^x":
                return "powten";
            case "1/x":
                return "reciproc";
            default:
                return operation;
        }
    }

}

