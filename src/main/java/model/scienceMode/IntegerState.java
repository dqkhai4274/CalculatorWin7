package model.scienceMode;

import model.ModelExpression;
import model.State;

import static model.ModelExpression.simplify;

public class IntegerState implements State {
    private int count = 0;
    private ScientificExpression model;

    public IntegerState(ScientificExpression model) {
        this.model = model;
    }

    public void clickOperation(String message) {
        State s =new OperationState(model);
        // change state is important, if not it can add a lot of operand
        model.changeState(s);
        model.clickOperation(message);
    }

    @Override
    public void clickDigit(String message) {
        int num = Integer.parseInt(message);
        // 10 * oldDigit + current Digit
        model.setOperand(model.getOperand()*10+num);
        // add new digit to expression
        // if getOperand return 0, that mean, last item in stack is operation
        if(count == 0){
            model.addToExpression(simplify(model.getOperand()));
        }else{
            model.updateLastElement(simplify(model.getOperand()));
        }
        count++;
    }

    @Override
    public void clickMemoryRecall() {

    }

    public void clickMonoOperation(String message) {
        // automatically add product to
        if(count > 0)
            model.addToExpression("*");
        model.addToExpression(ModelExpression.OperationToWord(message));
        model.addToExpression("(");
        model.changeState(new OperationState(model));
    }

    public void clickEqual(){

    }

    @Override
    public void clickDot() {
        model.updateLastElement(simplify(model.getOperand()) + ".");
        model.notifyObservers("", model.getExpression());
        model.changeState(new DecimalState(model));
    }

    @Override
    public void clickUndo() {
        model.setOperand((int)(model.getOperand()/10));
        // update data
        model.updateLastElement(ModelExpression.simplify(model.getOperand()));
        model.notifyObservers("",model.getExpression());
    }
}
