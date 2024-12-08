package function;

import function.visitor.Visitor;

public class Div extends Binary {

    public Div(Function lhs, Function rhs) {
        super(lhs, rhs);
    }

    public String toString() {
        String lhsString = getLhs().toString();
        String rhsString = getRhs().toString();
        if (getLhs() instanceof Add || getLhs() instanceof Sub) {
            lhsString = "(" + lhsString + ")";
        }
        if (getRhs() instanceof Binary) {
            rhsString = "(" + rhsString + ")";
        }
        return lhsString + " / " + rhsString;
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}
