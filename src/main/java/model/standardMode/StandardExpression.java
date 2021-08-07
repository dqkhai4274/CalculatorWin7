package model.standardMode;

import model.ModelExpression;

public class StandardExpression extends ModelExpression {

    public static final String[] clear = {"CE", "C"};
    public static final String operations = "+-*/";
    public static final String[] monoOperations = {"NE", "sqrt", "%", "1/x"};

    public StandardExpression(){
        this.state = new IntegerState(this);
    }

    public void clickOperation(String message){
        state.clickOperation(message);
    }

    public void clickDigit(String message){
        state.clickDigit(message);
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

    public void clickMonoOperation(String message) {
        try {
            state.clickMonoOperation(message);
        } catch (ArithmeticException e) {
            notifyObservers("Cant divide by zero", "recipoc(0)");
        }
    }

}
