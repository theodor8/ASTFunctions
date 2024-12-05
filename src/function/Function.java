package function;

import function.visitor.Visitor;

public abstract class Function {

    public abstract Function accept(Visitor v);

}
