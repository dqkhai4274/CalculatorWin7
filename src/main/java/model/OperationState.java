package model;

import MyException.NotANumberException;

public class OperationState implements State{
    private StandardExpression expr;
    private double tempOperand;

    public OperationState(StandardExpression expr){
        this.expr = expr;
    }

    @Override
    public void clickOperation(String message) {
        // store temporary operand's value to this variable
        tempOperand = expr.getOperand();
        expr.setOperation(message);
        // update old display with new operation
        String tmp = StandardExpression.simplify(expr.getOperand());
        expr.notifyObservers(tmp,tmp+ message);
    }

    @Override
    public void clickDigit(String message) {
        expr.setOperand1(expr.getOperand());
        expr.setOperand(0);
        State s = new IntegerState(expr);
        expr.changeState(s);
        s.clickDigit(message);
    }

    @Override
    public void clickMemoryRecall() {
        tempOperand = expr.getMemory();
        expr.notifyObservers(StandardExpression.simplify(tempOperand), "");
    }

    @Override
    public void clickExtendedOperation(String message) throws NotANumberException {
        String tmp = StandardExpression.simplify(expr.getOperand());
        String oldMess = String.format("%s %s %s(%s)", tmp, expr.getOperation(), message, tmp);
        tempOperand = StandardExpression.extendedCalculate(expr.getOperand(), message);
        expr.notifyObservers(StandardExpression.simplify(expr.getOperand()), oldMess);
    }

    @Override
    public void clickEqual() {
        // when you click right after you enter operation
        expr.setResult(ModelExpression.calculate(expr.getOperand(), tempOperand, expr.getOperation()));
        expr.notifyObservers(StandardExpression.simplify(expr.getResult()), " ");
        // after done calculating, change to result state
        expr.changeState(new ResultState(expr));
    }

    @Override
    public void clickDot() {
        expr.setOperand1(expr.getOperand());
        expr.setOperand(0);
        expr.notifyObservers("0.", "");
        expr.changeState(new DecimalState(expr));
    }

    @Override
    public void clickUndo() {
        //do nothing
    }
}
