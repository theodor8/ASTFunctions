package function;

import function.visitor.Visitor;

public class Integral extends Unary {

    public Integral(Function arg) {
        super(arg);
    }

    @Override
    public String toString() {
        return "int(" + this.getArg().toString() + ")";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}
