package model.handler;

import model.ModelExpression;
import model.scienceMode.InfixExpression;

public class OperationHandler extends BaseHandler{

    public OperationHandler(ModelExpression model) {
        super(model);
    }

    public void handle(String message){
        message = ModelExpression.OperationToWord(message);
        if(model.getExpression().isEmpty())
            model.addToExpression("0");
        if(InfixExpression.isOperator(message)){
            // model.handler operator here
            if(!model.getExpression().isEmpty() && !InfixExpression.isOperator(model.getExpression().peek())){
                // if expression top's stack is not a operator
                model.addToExpression(message);
            }else{
                model.updateLastElement(message);
            }
            model.notifyObservers("", model.toString());
        }else{
            super.handle(message);
        }
    }
}
