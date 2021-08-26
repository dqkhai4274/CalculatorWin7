package model.standardMode;

import model.ModelExpression;
import model.handler.*;

public class StandardExpression extends ModelExpression {

    public StandardExpression(){
        setHandler(0, new DigitHandler(this));
        setHandler(1, new MonoOperationHandler(this));
        setHandler(2, new OperationHandler(this));
        setHandler(3, new EqualHandler(this));
        setHandler(4, new DotHandler(this));
        setHandler(5, new UndoHandler(this));
        setHandler(6, new ClearHandler(this));
        setHandler(7, new MemoryHandler(this));
    }

}
