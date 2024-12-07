package function;

import function.visitor.Visitor;

public class Exp extends Unary {

    public Exp(Function arg) {
        super(arg);
    }

    @Override
    public String toString() {
        return "exp(" + this.getArg().toString() + ")";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}


