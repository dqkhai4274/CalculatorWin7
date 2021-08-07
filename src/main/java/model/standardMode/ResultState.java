package model.standardMode;

import model.ModelExpression;
import model.scienceMode.InfixExpression;
import model.State;

public class ResultState implements State {
    private ModelExpression expr;

    public ResultState(ModelExpression expr){
        this.expr = expr;
    }

    @Override
    public void clickOperation(String message) {
        expr.setOperation(message);
        // result become new operand
        expr.setOperand(expr.getResult());
        String tmp = ModelExpression.simplify(expr.getResult());
        expr.notifyObservers(tmp,tmp + message);
        expr.changeState(new OperationState(expr));

    }

    @Override
    public void clickDigit(String message) {
        //when click digit, result is delete, start again
        expr.setResult(0);
        expr.setOperand(Integer.parseInt(message));
        expr.notifyObservers(message, " ");
        expr.changeState(new IntegerState(expr));
    }

    @Override
    public void clickMemoryRecall() {
        expr.setResult(expr.getMemory());
        expr.notifyObservers(StandardExpression.simplify(expr.getResult()), "");
    }

    @Override
    public void clickMonoOperation(String message){
        expr.setOperand(expr.getResult());
        State s = new IntegerState(expr);
        expr.changeState(s);
        s.clickMonoOperation(message);
    }

    @Override
    public void clickEqual() {
        // when click equal, it will perform the same operation between last operand and result
        expr.setResult(InfixExpression.calculate(expr.getResult(),expr.getOperand(), expr.getOperation()));
        expr.notifyObservers(StandardExpression.simplify(expr.getResult()), " ");
    }

    @Override
    public void clickDot() {
        // when click dot, operand reset to 0, and move to decimal state
        expr.setOperand(0);
        expr.notifyObservers("0.","");
        expr.changeState(new DecimalState(expr));
    }

    @Override
    public void clickUndo() {
        //do nothing
    }
}
