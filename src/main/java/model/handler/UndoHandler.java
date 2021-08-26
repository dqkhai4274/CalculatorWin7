package model.handler;

import model.ModelExpression;

public class UndoHandler extends BaseHandler{

    public UndoHandler(ModelExpression model) {
        super(model);
    }

    @Override
    public void handle(String message) {
        String s;
        if(message.equals("<--")){
            if(!model.getExpression().isEmpty()){
                String tmp = model.getExpression().pop();
                // if top stack is a decimal, delete the last digit, otherwise pop it out of stack
                if(isDecimal(tmp) && tmp.length() > 1){
                    s = tmp.substring(0, tmp.length()-1);
                    model.addToExpression(s);
                }
                System.out.println(model.getExpression());
                model.notifyObservers("", model.toString());
            }
        }else{
            super.handle(message);
        }
    }
}
