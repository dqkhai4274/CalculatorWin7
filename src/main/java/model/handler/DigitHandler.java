package model.handler;

import model.ModelExpression;

public class DigitHandler extends BaseHandler{
    public static final String digits = "0123456789";

    public DigitHandler(ModelExpression model) {
        super(model);
    }

    public void handle(String message){
        if(digits.contains(message)){
            if(model.getExpression().isEmpty()){
                model.addToExpression(message);
            }else{
                if(isDecimal(model.getExpression().peek())){
                    String tmp = model.getExpression().peek() + message;
                    model.updateLastElement(tmp);
                }else{
                    // if expression top's stack is not a decimal number
                    model.addToExpression(message);
                }
            }
            model.notifyObservers("", model.toString());
        }else{
            // otherwise let other handle message
            super.handle(message);
        }
    }

}
