package function;

import function.visitor.Visitor;

public class Neg extends Unary {

    public Neg(Function arg) {
        super(arg);
    }

    @Override
    public String toString() {
        return "-" + arg.toString();
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }
}
