package model.scienceMode;

import model.ModelExpression;
import model.State;

public class DecimalState implements State {
    private double coeff = 0.1;
    private int numBehindDot = 1;
    private ScientificExpression model;


    public DecimalState(ScientificExpression model) {
        this.model = model;
    }

    public void clickOperation(String message) {
        State s = new OperationState(model);
        s.clickOperation(message);
        // change state is important, if not it can add a lot of operand
        model.changeState(s);
    }

    @Override
    public void clickDigit(String message) {
        int num = Integer.parseInt(message);
        numBehindDot += 1;
        model.setOperand(round(model.getOperand() + coeff * num, numBehindDot));
        coeff /= 10;
        model.updateLastElement(ModelExpression.simplify(model.getOperand()));
        model.notifyObservers("", model.getExpression());
    }

    private static double round(double a, int num) {
        double temp = a * Math.pow(10, num);
        temp = Math.round(temp);
        return temp / Math.pow(10, num);
    }

    @Override
    public void clickMemoryRecall() {

    }

    public void clickMonoOperation(String message) {
        // automatically add product to
        model.addToExpression("*");
        model.addToExpression(ModelExpression.OperationToWord(message));
        model.addToExpression("(");

        model.changeState(new OperationState(model));
    }

    public void clickEqual() {

    }

    @Override
    public void clickDot() {
        // nothing happend
    }

    @Override
    public void clickUndo() {
        String tmp = Double.toString(model.getOperand());
        tmp = tmp.substring(0, tmp.length() - 1);
        model.setOperand(Double.parseDouble(tmp));
        numBehindDot--;
        if (numBehindDot == 0) {
            // delete the . symbol then change state
            tmp = tmp.substring(0, tmp.length() - 1);
            model.notifyObservers(tmp, "");
            model.changeState(new IntegerState(model));
        }
        model.updateLastElement(ModelExpression.simplify(model.getOperand()));
        model.notifyObservers("", model.getExpression());

    }
}
