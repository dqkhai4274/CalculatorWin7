package model.scienceMode;

import model.ModelExpression;

import java.util.EmptyStackException;
import java.util.Stack;

public class ScientificExpression extends ModelExpression {
    private Stack<String> expression = new Stack<>();
    private InfixExpression calculator;

    public ScientificExpression(){
        state = new IntegerState(this);
        calculator = new InfixExpression();
    }

    public void addToExpression(String s){
        expression.push(s);
    }

    public void updateLastElement(String s){
        if(!expression.isEmpty())
            expression.pop();
        expression.push(s);
    }

    @Override
    public void clickDigit(String message) {
        state.clickDigit(message);
    }

    @Override
    public void clickOperation(String message) {
        state.clickOperation(message);
        notifyObservers("", getExpression());
    }

    @Override
    public void clickMonoOperation(String message) {
        state.clickMonoOperation(message);
        notifyObservers("", getExpression());
    }

    @Override
    public void clickEqual() {
        try{
            double result = calculator.evaluate(expression);
            notifyObservers(simplify(result), "");
        }catch(EmptyStackException | ArithmeticException e){
            System.err.println("Syntax error!");
            notifyObservers("Syntax error", "");
        }
        reset();
    }

    @Override
    public void clickDot() {
        state.clickDot();
    }

    @Override
    public void clickUndo() {
        state.clickUndo();
    }

    @Override
    public void clickParentheses(String message){
        // if last character in are operator, add an *
        if(!InfixExpression.isOperator(expression.peek()) && message == "(")
            addToExpression("*");
        addToExpression(message);
        notifyObservers("", getExpression());
    }

    @Override
    public void clickClear(String message){
        super.clickClear(message);
        expression = new Stack<>();
        state = new IntegerState(this);
    }

    public String getExpression(){
        StringBuffer buffer = new StringBuffer();
        for(String s : expression){
            buffer.append(s);
        }
        return buffer.toString();
    }

    public void reset(){
        setOperand(0);
        setOperand1(0);
        setResult(0);
        setOperation("");
        state = new IntegerState(this);
        expression = new Stack<>();
    }
}
