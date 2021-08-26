package model.handler;

import model.scienceMode.ScientificExpression;

public class PiHandler extends BaseHandler{
    public PiHandler(ScientificExpression model) {
        super(model);
    }

    public void handle(String message){
        if(message.equals("π")){
            if(!model.getExpression().isEmpty() && isDecimal(model.getExpression().peek())){
                model.addToExpression("*");
            }
            model.addToExpression(String.valueOf(Math.PI));
            model.notifyObservers("", model.toString());
        }else{
            super.handle(message);
        }
    }
}
