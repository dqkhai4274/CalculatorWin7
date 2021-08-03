package model;

import MyException.NotANumberException;
import view.Button.MyButton;

import java.util.ArrayList;
import java.util.List;

public class StandardExpression extends ModelExpression {
    private List<ModelObserver> observers = new ArrayList<>();
    private String operation = "";
    private double memory;
    private double result;
    private double operand;
    private double operand1;
    private String lastMessage="0";
    private State state;

    public StandardExpression(){
        this.state = new IntegerState(this);
    }

    public void changeState(State state){
        this.state = state;
    }

    public void clickBasicOperation(String message){
        state.clickOperation(message);
    }

    public void clickDigit(String message){
        state.clickDigit(message);
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
            clickClear("C");
        }
    }

    public void clickEqual(){
        try{
            state.clickEqual();
        }catch(ArithmeticException e){
            notifyObservers("Cant divide by 0", " ");
        }
    }

    public void clickDot() { state.clickDot(); }

    public void clickUndo(){
        state.clickUndo();
    }

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

    public void clickExtendedOperation(String message) {
        try {
            state.clickExtendedOperation(message);
        } catch (NotANumberException e1) {
            notifyObservers("Invalid input", String.format("sqrt(-)"));
        } catch (ArithmeticException e) {
            notifyObservers("Cant divide by zero", "recipoc(0)");
        }
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

    public double getMemory(){
        return this.memory;
    }

    @Override
    public void update(MyButton button, String message) {
        button.click(message);
    }

    public static String simplify(double d){
        // if result is a integer, simplify it
        if(d - (int)d == 0){
            return String.valueOf((int)d);
        }
        return String.valueOf(d);
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
}
