package model.scienceMode;

import model.handler.*;
import model.ModelExpression;

public class ScientificExpression extends ModelExpression {

    public ScientificExpression(){
        setHandler(0, new DigitHandler(this));
        setHandler(1, new MonoOperationHandler(this));
        setHandler(2, new OperationHandler(this));
        setHandler(3, new EqualHandler(this));
        setHandler(4, new DotHandler(this));
        setHandler(5, new ParenthesesHandler(this));
        setHandler(6, new PiHandler(this));
        setHandler(7, new UndoHandler(this));
        setHandler(8, new ClearHandler(this));
        setHandler(9, new MemoryHandler(this));
    }
}
