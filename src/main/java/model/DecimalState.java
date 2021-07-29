package model;

import MyException.NotANumberException;

public class DecimalState implements State{
    private double coeff = 0.1;
    private int numBehindDot = 1;
    private StandardExpression expr;

    public DecimalState(StandardExpression expr){
        this.expr = expr;
    }

    @Override
    public void clickOperation(String message) {
        // user click a operation, store operand to result and store operation.
        expr.setOperation(message);
        // update old display with operation
        String tmp =String.valueOf(expr.getOperand());
        expr.notifyObservers(tmp, tmp+message);
        expr.changeState(new OperationState(expr));
    }

    @Override
    public void clickDigit(String message) {
        int num = Integer.parseInt(message);
        numBehindDot+=1;
        expr.setOperand(round(expr.getOperand()+coeff*num, numBehindDot));
        coeff /= 10;
        expr.notifyObservers(String.valueOf(expr.getOperand()), "");
    }

    private static double round(double a, int num){
        double temp = a * Math.pow(10, num);
        temp = Math.round(temp);
        return temp/Math.pow(10, num);
    }

    @Override
    public void clickMemoryRecall() {
        State s = new ResultState(expr);
        expr.changeState(s);
        s.clickMemoryRecall();
    }

    @Override
    public void clickExtendedOperation(String message) throws NotANumberException {
        State s = new IntegerState(expr);
        s.clickExtendedOperation(message);
    }

    @Override
    public void clickEqual() {
        //when user click equal, 2 cases
        expr.setResult(ModelExpression.calculate(expr.getOperand1(),expr.getOperand(), expr.getOperation()));
        if(coeff == 0.1){
            expr.notifyObservers(StandardExpression.simplify(expr.getResult()), " ");
        }else{
            expr.notifyObservers(String.valueOf(round(expr.getResult(), numBehindDot))," ");
        }
        expr.changeState(new ResultState(expr));
    }

    @Override
    public void clickDot() {
        //nothing happend
    }

    @Override
    public void clickUndo() {
        String tmp = Double.toString(expr.getOperand());
        tmp = tmp.substring(0, tmp.length()-1);
        expr.setOperand(Double.parseDouble(tmp));
        numBehindDot--;
        if(numBehindDot == 0){
            // delete the . symbol then change state
            tmp = tmp.substring(0, tmp.length()-1);
            expr.notifyObservers(tmp,"" );
            expr.changeState(new IntegerState(expr));
        }
        expr.notifyObservers(tmp,"");
    }
}
