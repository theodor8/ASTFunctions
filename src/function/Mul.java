package function;

import function.visitor.Visitor;

public class Mul extends Binary {

    public Mul(Function lhs, Function rhs) {
        super(lhs, rhs);
    }

    @Override
    public String toString() {
        String lhsString = lhs.toString();
        String rhsString = rhs.toString();
        if (lhs instanceof Add || lhs instanceof Sub) {
            lhsString = "(" + lhsString + ")";
        }
        if (rhs instanceof Add || rhs instanceof Sub) {
            rhsString = "(" + rhsString + ")";
        }
        return lhsString + " * " + rhsString;
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}
