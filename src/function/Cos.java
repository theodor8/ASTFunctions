package function;

import function.visitor.Visitor;

public class Cos extends Unary {

    public Cos(Function arg) {
        super(arg);
    }

    public String toString() {
        return "cos(" + this.getArg().toString() + ")";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }
}
