package function;

import function.visitor.Visitor;

public class Mul extends Binary {

    public Mul(Function lhs, Function rhs) {
        super(lhs, rhs);
    }

    @Override
    public String toString() {
        String lhsString = getLhs().toString();
        String rhsString = getRhs().toString();
        if (getLhs() instanceof Add || getLhs() instanceof Sub) {
            lhsString = "(" + lhsString + ")";
        }
        if (getRhs() instanceof Add || getRhs() instanceof Sub) {
            rhsString = "(" + rhsString + ")";
        }
        return lhsString + " * " + rhsString;
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}
