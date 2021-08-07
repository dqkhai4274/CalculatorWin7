package model.scienceMode;

import model.ModelExpression;
import model.State;

public class OperationState implements State {
    private int count;
    private ScientificExpression model;
    private double tempOperand;

    public OperationState(ScientificExpression model){
        this.model = model;
    }

    @Override
    public void clickOperation(String message) {
        // store temporary operand's value to this variable
        tempOperand = model.getOperand();
        model.setOperation(message);
        // first time, just add, otherwise mean that you want to change operation
        if(count == 0){
            model.addToExpression(ModelExpression.OperationToWord(message));
        }else{
            model.updateLastElement(ModelExpression.OperationToWord(message));
        }
        count++;
    }

    public void clickDigit(String message){
        model.setOperand1(model.getOperand());
        model.setOperand(0);
        State s = new IntegerState(model);
        model.changeState(s);
        model.clickDigit(message);
    }

    @Override
    public void clickMemoryRecall() {
        tempOperand = model.getMemory();
        model.notifyObservers(ModelExpression.simplify(tempOperand), "");
    }

    public void clickMonoOperation(String message){
        model.addToExpression(ModelExpression.OperationToWord(message));
        model.addToExpression("(");
    }

    public void clickEqual(){

    }

    @Override
    public void clickDot() {
        model.setOperand1(model.getOperand());
        model.setOperand(0);
        model.changeState(new DecimalState(model));
        model.addToExpression("0.");
        model.notifyObservers("", model.getExpression());
    }

    @Override
    public void clickUndo() {
        //do nothing
    }
}
