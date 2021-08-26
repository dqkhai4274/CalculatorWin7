package model.handler;

import MyException.NotANumberException;
import model.ModelExpression;
import model.scienceMode.InfixExpression;
import model.scienceMode.ScientificExpression;

import java.util.EmptyStackException;

public class EqualHandler extends BaseHandler{
    private InfixExpression calculator = new InfixExpression();

    public EqualHandler(ModelExpression model) {
        super(model);
    }

    public void handle(String message){
        if(message.equals("=")){
            //handle = here
            try{
                model.setPreviousResult(calculator.evaluate(model.getExpression()));
                model.notifyObservers(ModelExpression.simplify(model.getPreviousResult()), "");
                model.reset();
            }catch(NotANumberException | EmptyStackException e){
                model.setPreviousResult(0);
                model.notifyObservers("", "Syntax Error or undefined input");
                model.reset();
            }
        }else{
            super.handle(message);
        }
    }
}