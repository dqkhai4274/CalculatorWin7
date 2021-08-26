package model.handler;

import model.ModelExpression;

public class MemoryHandler extends BaseHandler{
    public MemoryHandler(ModelExpression model) {
        super(model);
    }

    @Override
    public void handle(String message) {
        switch (message){
            case "MC":
                model.setMemory(0);
                break;
            case "MR":
                model.addToExpression(ModelExpression.simplify(model.getMemory()));
                model.notifyObservers(" ", model.toString());
                break;
            case "MS":
                model.setMemory(model.getPreviousResult());
                break;
            case "M+":
                model.setMemory(model.getPreviousResult()+ model.getMemory());
                break;
            case "M-":
                model.setMemory(model.getMemory() - model.getPreviousResult());
                break;
            default:
                super.handle(message);
        }

    }
}
