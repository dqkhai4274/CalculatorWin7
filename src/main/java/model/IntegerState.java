package model;

import MyException.NotANumberException;

public class IntegerState implements State{
    private StandardExpression expr;

    public IntegerState(StandardExpression expr){
        this.expr = expr;
    }

    @Override
    public void clickOperation(String message) {
        State s =new OperationState(expr);
        s.clickOperation(message);
        expr.changeState(s);
    }

    @Override
    public void clickDigit(String message) {
        int num = Integer.parseInt(message);
        // 10 * oldDigit + current Digit
        expr.setOperand(expr.getOperand()*10+num);
        expr.notifyObservers(String.format("%d", (int)expr.getOperand()), "");
    }

    @Override
    public void clickMemoryRecall() {
        State s = new ResultState(expr);
        expr.changeState(s);
        s.clickMemoryRecall();
    }

    @Override
    public void clickExtendedOperation(String message) throws NotANumberException, ArithmeticException{
        double old = expr.getOperand();
        expr.setOperand(StandardExpression.extendedCalculate(expr.getOperand(), message));
        expr.notifyObservers(StandardExpression.simplify(expr.getOperand()),
                String.format("%s(%s)", message, StandardExpression.simplify(old)));
    }

    @Override
    public void clickEqual() {
        expr.setResult(ModelExpression.calculate(expr.getOperand1(),expr.getOperand(), expr.getOperation()));
        // update this result to entry display, and clear old display
        expr.notifyObservers(StandardExpression.simplify(expr.getResult()), " ");
        expr.changeState(new ResultState(expr));
    }

    @Override
    public void clickDot() {
        expr.notifyObservers(String.format("%d.", (int)expr.getOperand()), "");
        expr.changeState(new DecimalState(expr));
    }

    @Override
    public void clickUndo(){
        expr.setOperand((int)(expr.getOperand()/10));
        expr.notifyObservers(StandardExpression.simplify(expr.getOperand()),"");
    }
}
