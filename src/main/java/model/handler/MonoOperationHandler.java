package model.handler;

import model.ModelExpression;
import model.scienceMode.InfixExpression;

public class MonoOperationHandler extends BaseHandler{

    public MonoOperationHandler(ModelExpression model) {
        super(model);
    }

    public void handle(String message){
        if(InfixExpression.isMonoOperator(ModelExpression.OperationToWord(message))){
            // handle monoOperation here
            if(!model.getExpression().isEmpty() && isDecimal(model.getExpression().peek())){
                model.addToExpression("*");
            }
            model.addToExpression(ModelExpression.OperationToWord(message));
            model.addToExpression("(");
            model.notifyObservers("", model.toString());

        }else{
            super.handle(message);
        }
    }
}
