package function;

import function.visitor.Visitor;

public class Abs extends Unary {

    public Abs(Function arg) {
        super(arg);
    }

    @Override
    public String toString() {
        return "abs(" + getArg().toString() + ")";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}
