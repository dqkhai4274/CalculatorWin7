package model.handler;

import model.ModelExpression;

public class DotHandler extends BaseHandler{

    public DotHandler(ModelExpression model) {
        super(model);
    }

    public void handle(String message){
        // in case message is dot or last stack not already contain a dot
        if(message.equals(".") && !model.getExpression().peek().contains(".")){
            if(model.getExpression().isEmpty() || !isDecimal(model.getExpression().peek())) {
                // if expression is empty or last stack is not a digit
                model.addToExpression("0.");
            }else{
                model.updateLastElement(model.getExpression().peek() + ".");
            }
            model.notifyObservers("", model.toString());
        }else{
            super.handle(message);
        }
    }
}
