package function;

import function.visitor.Visitor;

public class Derivative extends Unary {

    public Derivative(Function arg) {
        super(arg);
    }

    @Override
    public String toString() {
        return "d(" + this.arg.toString() + ")";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }
    
}
