package model.handler;

import model.scienceMode.InfixExpression;
import model.scienceMode.ScientificExpression;

public class ParenthesesHandler extends BaseHandler{
    public ParenthesesHandler(ScientificExpression model) {
        super(model);
    }

    public void handle(String message){
        if(message.equals("(") || message.equals(")")){
            if(!model.getExpression().isEmpty() && !InfixExpression.isOperator(model.getExpression().peek()) && message.equals("(")) {
                model.addToExpression("*");
            }
            model.addToExpression(message);
            model.notifyObservers("", model.toString());

        }else{
            super.handle(message);
        }
    }
}
