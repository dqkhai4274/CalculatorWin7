package model.handler;

import model.ModelExpression;

public class ClearHandler extends BaseHandler{

    public ClearHandler(ModelExpression model) {
        super(model);
    }

    public void handle(String message){
        if(message.equals("CE") || message.equals("C")){
            model.reset();
            model.notifyObservers("", " ");
        }else{
            super.handle(message);
        }
    }
}
