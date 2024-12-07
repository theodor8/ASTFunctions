package function;

import function.visitor.Visitor;

public class Sin extends Unary {

    public Sin(Function arg) {
        super(arg);
    }

    public String toString() {
        return "sin(" + this.getArg().toString() + ")";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }
}
