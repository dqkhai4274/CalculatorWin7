package model;

import model.handler.Handler;
import view.keyboard.KeyBoardObserver;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class ModelExpression implements KeyBoardObserver, ModelPublisher {
    private List<ModelObserver> observers = new ArrayList<>();
    private Stack<String> expression = new Stack<>();
    private ArrayList<Handler> handlers = new ArrayList<>();
    private double previousResult;
    private double memory;

    @Override
    public void register(ModelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String messageForEntry, String messageForOld) {
        for(ModelObserver observer: observers){
            observer.update(messageForEntry, messageForOld);
        }
    }

    public void addToExpression(String s){
        expression.push(s);
    }

    public void updateLastElement(String s){
        if(!expression.isEmpty())
            expression.pop();
        expression.push(s);
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        for(String s : expression){
            buffer.append(s);
        }
        return buffer.toString();
    }

    public Stack<String> getExpression(){
        return this.expression;
    }

    public void reset(){
        this.expression = new Stack<>();
    }

    protected void setHandler(int i, Handler handler){
        handlers.add(i, handler);
        if(i > 0) {
            handlers.get(i - 1).setNext(handler);
            handler.setNext(null);
        }
    }

    @Override
    public void update(String message){
        // let handler handle message
        handlers.get(0).handle(message);
    }

    public void setPreviousResult(double previousResult) {
        this.previousResult = previousResult;
    }

    public double getPreviousResult() {
        return previousResult;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
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

