package function;

import function.visitor.Visitor;

public class Pow extends Binary {

    public Pow(Function lhs, Function rhs) {
        super(lhs, rhs);
    }

    @Override
    public String toString() {
        return "(" + lhs + ")^(" + rhs + ")";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}
