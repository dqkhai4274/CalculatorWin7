package model.handler;

import model.ModelExpression;

public class BaseHandler implements Handler{
    private Handler nextHandler;
    protected ModelExpression model;

    public BaseHandler(ModelExpression model){
        this.model = model;
    }

    @Override
    public void setNext(Handler h) {
        nextHandler = h;
    }

    @Override
    public void handle(String message) {
        if(nextHandler != null){
            nextHandler.handle(message);
        }
    }

    public static boolean isDecimal(String message){
        try{
            double d = Double.parseDouble(message);
            return true;
        }catch(NumberFormatException | NullPointerException e){
            return false;
        }
    }
}
